package gui;


import detail.models.EDAResultModel;
import detail.config.JStatGuiGlobalData;
import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.TaskBase;
import mongodb.ComputeTasksControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/analysis-result")
public class AnalysisResult {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${collections.control.tasks}")
    String controlColection;

    @GetMapping
    public String analysisView(@RequestParam("controlTaskId") String controlTaskId, Model model){


        // find the document that corresponds
        // to this control Task
        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlColection);

        if(doc == null){
            throw new IllegalArgumentException("No control task for id: "+controlTaskId);
        }

        // if we haven't finished return
        if(!doc.isFinished()){
            model.addAttribute("controlTaskId", controlTaskId);
            return "analysis_result_descriptive_stats";
        }
        /*else{
            model.addAttribute("controlTaskId", controlTaskId );
            return "analysis_result_descriptive_stats";
        }*/


        model.addAttribute("controlTaskId", controlTaskId );
        return "analysis_result_descriptive_stats";
    }

    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("controlTaskId") String controlTaskId){

        //TaskBase task = JStatGuiGlobalData.getTask(taskName);

        // find the document that corresponds
        // to this control Task
        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlColection);

        if(doc == null){
            throw new IllegalArgumentException("No control task for id: "+controlTaskId);
        }

        // redirect to the analysis page again
        return "redirect:/analysis-result/?controlTaskId="+controlTaskId;
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
