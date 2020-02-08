package gui.controllers;

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
import tech.tablesaw.columns.Column;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analysis")
public class Analysis {


    @GetMapping
    public String analysisView(Model model){

        // in order to do an analysis we need the list of the loaded data sets
        List<String> dataSetNames = JStatGuiGlobalData.dataSetContainer.dataSetNames();

        if(dataSetNames.size() == 0){
            dataSetNames.add("No datasets loaded");
        }

        List<DataSetViewInfoHolder> dataSets = new ArrayList<>();

        for(int i=0; i<dataSetNames.size(); ++i){

            IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetNames.get(i));

            List<String> cols = new ArrayList<>();
            cols.add("All");

            List<String> dataSetCols = dataSet.getColumnNames();

            for(int name=0; name<dataSetCols.size(); ++name){
                cols.add(dataSetCols.get(name));
            }

            DataSetViewInfoHolder holder = new DataSetViewInfoHolder();
            holder.name = dataSet.getName();
            holder.names = cols;

            dataSets.add(holder);
        }

        model.addAttribute("dataSets", dataSets);
        return "analysis";
    }

    @PostMapping
    public String handleAnalysisForm(/*@RequestParam("taskName") String taskName,*/ @Valid AnalysisFormWrapper formWrapper, Errors errors){

        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "/analysis";
        }

        // Try to figure out what sort of
        // analysis we want to do
        if(formWrapper.eda != null){

            this.computeDataSetStatistics(formWrapper);
            return "redirect:/analysis-result?taskName=" + "EDA"+formWrapper.colName;
        }
        else if(formWrapper.linear_regression != null){

        }
        else if(formWrapper.non_linear_regression != null){

        }
        else if(formWrapper.logistic_regression != null){

        }
        else if(formWrapper.kmeans_clustering != null){

        }
        else{

            // return an error message that no valid action
            // was selected
        }

        // redirect to the analysis page again
        return "redirect:/analysis";
    }

    protected void computeDataSetStatistics(AnalysisFormWrapper formWrapper){


        System.out.println("================");
        System.out.println("computeDataSetStatistics...");
        System.out.println("================");
        System.out.println("Filename exploreDataSet: "+formWrapper.eda);
        System.out.println("Filename exploreDataSet: "+formWrapper.dataSetName);
        System.out.println("Column Name: " + formWrapper.colName);

        // get the name of the dataset
        String dataSetName = formWrapper.dataSetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetName);

        if (dataSet == null) {
            System.out.println("dataSet is null");
        }

        if(formWrapper.colName == "All"){

            List<String> dataSetCols = dataSet.getColumnNames();
            String[] names = new String[dataSetCols.size()];

            for(int i=0; i<names.length; ++i){
                names[i] = dataSetCols.get(i);
            }

            // submit it to the pool
            TaskBase task = new ComputeDescriptiveStatisticsTask<Column>("EDA"+"All", dataSet, names);
            JStatGuiGlobalData.workersPool.submit(task);
            JStatGuiGlobalData.tasks.add(task);

        }
        else{

            // submit it to the pool
            TaskBase task = new ComputeDescriptiveStatisticsTask<Column>("EDA"+formWrapper.colName, dataSet, formWrapper.colName);
            JStatGuiGlobalData.workersPool.submit(task);
            JStatGuiGlobalData.tasks.add(task);
        }
    }
}
