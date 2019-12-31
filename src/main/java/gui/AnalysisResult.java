package gui;


import detail.config.JStatGuiGlobalData;
import detail.datasets.DataSetViewInfoHolder;
import detail.datasets.IDataSet;
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

    @GetMapping
    public String analysisView(@RequestParam("taskName") String taskName, Model model){

        //String taskIdentifier =

        TaskBase task = JStatGuiGlobalData.getTask(taskName);

        if(task != null){
            System.out.println("Task with name: "+taskName+" exists");
        }
        else{
            System.out.println("Task with name: "+taskName+" does not exist");
        }

        model.addAttribute("taskName", taskName);

        /*List<DataSetViewInfoHolder> dataSets = new ArrayList<>();

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

        model.addAttribute("dataSets", dataSets);*/
        return "analysis_result_descriptive_stats";
    }

    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("taskName") String taskName/*@Valid AnalysisFormWrapper formWrapper, Errors errors*/){

        TaskBase task = JStatGuiGlobalData.getTask(taskName);

        /*try {
            wait(1000);
        }
        catch (InterruptedException e){

        }*/

        // validate form
        /*if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "/analysis";
        }

        // Try to figure out what sort of
        // analysis we want to do
        if(formWrapper.eda != null){

            this.computeDataSetStatistics(formWrapper);
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
        }*/

        // redirect to the analysis page again
        return "redirect:/analysis-result?taskName="+taskName;
    }
}
