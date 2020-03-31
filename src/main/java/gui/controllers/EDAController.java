package gui.controllers;

import detail.compute.ComputationModelType;
import detail.config.JStatGuiGlobalData;
import detail.datasets.DataSetViewInfoHolder;
import detail.datasets.IDataSet;
import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.ComputeTasksController;
import detail.tasks.TaskBase;
import detail.tasks.utils.DescriptiveStatisticsDBWritePolicy;
import gui.wrappers.EDAAnalysisFormWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("eda_view")
public class EDAController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping
    public String edaAnalysisView(@RequestParam String taskName, Model model) {


        // in order to do an analysis we need the list of the loaded data sets
        List<String> dataSetNames = JStatGuiGlobalData.dataSetContainer.dataSetNames();

        if (dataSetNames.size() == 0) {
            throw new IllegalArgumentException("Data size SHOULD NOT be zero.");
        }

        List<DataSetViewInfoHolder> dataSets = new ArrayList<>();

        for (int i = 0; i < dataSetNames.size(); ++i) {

            IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetNames.get(i));

            List<String> cols = new ArrayList<>();
            cols.add("All");

            List<String> dataSetCols = dataSet.getColumnNames();

            for (int name = 0; name < dataSetCols.size(); ++name) {
                cols.add(dataSetCols.get(name));
            }

            DataSetViewInfoHolder holder = new DataSetViewInfoHolder();
            holder.name = dataSet.getName();
            holder.names = cols;

            dataSets.add(holder);
        }

        model.addAttribute("dataSets", dataSets);

        //model.addAttribute("taskName", taskName);

        return "eda_view";
    }

    @PostMapping
    public String handleEDARequest(@Valid EDAAnalysisFormWrapper formWrapper, Errors errors) {
        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "eda_view";
        }
        String id = this.createComputeTasks(formWrapper);
        // redirect to the analysis page again
        return "redirect:/eda_view_results?controlTaskId=" + id;
    }

    protected String createComputeTasks(EDAAnalysisFormWrapper formWrapper) {

        System.out.println("Filename exploreDataSet: " + formWrapper.dataSetName);
        System.out.println("Column Name: " + formWrapper.colName);
        // get the name of the dataset

        String dataSetName = formWrapper.dataSetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetName);
        List<TaskBase> tasks = new ArrayList<>();

        if (formWrapper.colName.equals("All")) {

            List<String> dataSetCols = dataSet.getColumnNames();
            String[] names = new String[dataSetCols.size()];

            for (int i = 0; i < names.length; ++i) {
                names[i] = dataSetCols.get(i);
            }
            // for each names we generate a task
            for (int n = 0; n < names.length; ++n) {
                tasks.add(new ComputeDescriptiveStatisticsTask("EDA", dataSet, names[n]));
            }
        } else {
            // submit it to the pool
            TaskBase task = new ComputeDescriptiveStatisticsTask("EDA", dataSet, formWrapper.colName);
            tasks.add(task);
        }
        ComputeTasksController controlTask = new ComputeTasksController(JStatGuiGlobalData.workersPool,
                mongoTemplate, tasks,
                new DescriptiveStatisticsDBWritePolicy());

        controlTask.setComputationModelType(ComputationModelType.EDA);

        // save it so that we get a MongoDB id
        controlTask.save();

        // submit it to the pool
        JStatGuiGlobalData.workersPool.submit(controlTask);
        return controlTask.getId();
    }
}
