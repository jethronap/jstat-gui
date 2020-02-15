package gui.controllers;


import detail.compute.DescriptiveStats;
import detail.config.JStatGuiGlobalData;
import detail.datasets.DataSetViewInfoHolder;
import detail.datasets.IDataSet;
import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.TaskBase;
import detail.wrappers.AnalysisFormWrapper;
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
@RequestMapping("/analysis-result")
public class AnalysisResult {

    TaskBase task = null;
    List<DescriptiveStats> result;
    @GetMapping
    public String analysisView(@RequestParam("taskName") String taskName, Model model) {

        //String taskIdentifier =

        task = JStatGuiGlobalData.getTask(taskName);

        if (task != null) {
            System.out.println("Task with name: " + taskName + " exists");
        } else {
            System.out.println("Task with name: " + taskName + " does not exist");
        }

        if (result == null) {

            model.addAttribute("taskName", taskName);

        }
        else {
            for (int i = 0; i < result.size(); i++) {
                model.addAttribute("mean", result.get(i).mean);
            }
        }

        return "eda_results";
    }

    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("taskName") String taskName, AnalysisFormWrapper formWrapper /*Errors errors*/) throws Exception {


        task = JStatGuiGlobalData.getTask(taskName);

        System.out.println(formWrapper.colName);
        System.out.println(taskName);

        if (taskName != null) {

            System.out.println("we got here.");
            //System.out.println(task.call());

            if (task == null) {
                return "analysis_result_descriptive_stats";
            }

            result =((ComputeDescriptiveStatisticsTask) task).getResult();
            System.out.println("result size: "+ result.size());
            return "redirect:/analysis-result?taskName=" + taskName;
           // model.addAttribute("mean");
//          model.addAttribute("median");
//            model.addAttribute("variance");
        } else if (formWrapper.linear_regression != null) {

        } else if (formWrapper.non_linear_regression != null) {

        } else if (formWrapper.logistic_regression != null) {

        } else if (formWrapper.kmeans_clustering != null) {

        } else {

            // return an error message that no valid action
            // was selected
        }

        // redirect to the analysis page again
        return "index";
    }

    protected void computeDataSetStatistics(AnalysisFormWrapper formWrapper) {


        System.out.println("================");
        System.out.println("computeDataSetStatistics...");
        System.out.println("================");
        System.out.println("Filename exploreDataSet: " + formWrapper.eda);
        System.out.println("Filename exploreDataSet: " + formWrapper.dataSetName);
        System.out.println("Column Name: " + formWrapper.colName);

        // get the name of the dataset
        String dataSetName = formWrapper.dataSetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetName);

        if (formWrapper.colName == "All") {

            List<String> dataSetCols = dataSet.getColumnNames();
            String[] names = new String[dataSetCols.size()];

            for (int i = 0; i < names.length; ++i) {
                names[i] = dataSetCols.get(i);
            }

            // submit it to the pool
            TaskBase task = new ComputeDescriptiveStatisticsTask("EDA", dataSet, names);
            JStatGuiGlobalData.workersPool.submit(task);
            JStatGuiGlobalData.tasks.add(task);


        } else {

            // submit it to the pool
            TaskBase task = new ComputeDescriptiveStatisticsTask("EDA", dataSet, formWrapper.colName);
            JStatGuiGlobalData.workersPool.submit(task);
            JStatGuiGlobalData.tasks.add(task);
        }
    }
}
