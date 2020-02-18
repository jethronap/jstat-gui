package gui.controllers;


import gui.wrappers.AnalysisFormWrapper;
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

@Controller
@RequestMapping("eda_view_results")
public class EDAResultController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${collections.control.tasks}")
    String controlCollection;

    @GetMapping
    public String analysisView(@RequestParam("controlTaskId") String controlTaskId, Model model) {

        // find the document that corresponds
        // to this control Task
        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlCollection);

        if(doc == null){
            throw new IllegalArgumentException("No control task for id: "+controlTaskId);
        }

        // if we haven't finished return
        if(!doc.isFinished()){
            model.addAttribute("controlTaskId", controlTaskId);
            // msg to hit f5 prompt user to refresh somehow.
            return "eda_view_results";
        }
        /*else{
            model.addAttribute("controlTaskId", controlTaskId );
            return "analysis_result_descriptive_stats";
        }*/


        model.addAttribute("controlTaskId", controlTaskId );

        return "eda_view_results";
    }

    // TODO: probably mundane & should be removed
    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("controlTaskId") String controlTaskId, AnalysisFormWrapper formWrapper /*Errors errors*/) throws Exception {


        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlCollection);

        if(doc == null){
            throw new IllegalArgumentException("No control task for id: "+controlTaskId);
        }
        return "redirect:/eda_view_results?controlTaskId="+controlTaskId;
    }
//    private EDAResultModel getResults(TaskBase task){
//
//        /**
//         * i want to retrieve ids which are controlled by ComputeTasksControllerDoc
//         * for every such id i want the resultDoc from the db
//         * the put the model to serve them to the frontend
//         */
//
//        return EDAResultModel ;
//    }
}
