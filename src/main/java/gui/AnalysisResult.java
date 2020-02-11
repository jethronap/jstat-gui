package gui;


import detail.compute.EDAResultModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if(task != null){
            EDAResultModel result = getResults(task);
            model.addAttribute("results", result);
        }


        return "analysis_result_descriptive_stats";
    }

    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("taskName") String taskName/*@Valid AnalysisFormWrapper formWrapper, Errors errors*/){

        TaskBase task = JStatGuiGlobalData.getTask(taskName);



        // redirect to the analysis page again
        return "redirect:/analysis-result?taskName="+taskName;
    }

    private EDAResultModel getResults(TaskBase task){

        Map<String, String > result = new HashMap<>();

        /// this should actually be returned by the task
        ComputeDescriptiveStatisticsTask compTask = (ComputeDescriptiveStatisticsTask) task;

        if(task.finished()){

            /*result.put("Mean", Double.toHexString(13.0));
            result.put("Variance", Double.toHexString(13.0));
            result.put("Median", Double.toHexString(13.0));*/
        }

        return (EDAResultModel) compTask.getResult().get(0).getResultModel();
    }
}
