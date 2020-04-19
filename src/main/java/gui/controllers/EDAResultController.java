package gui.controllers;


import detail.models.EDAResultModel;
import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.TaskBase;
import detail.wrappers.AnalysisFormWrapper;
import mongodb.ComputeTasksControllerDoc;
import mongodb.DescriptiveStatisticsResultDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("eda_view_results")
public class EDAResultController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${collections.control.tasks}")
    String controlCollection;

    private EDAResultModel edaResultModel;

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
        //EDAResultModel resultModel = getResults(controlTaskId);
        List<EDAResultModel> resultModelList = getResults(controlTaskId);
        Map<String, Map<String, Double>> columns = new HashMap<>();

        for (int i = 0; i < resultModelList.size(); i++) {
            EDAResultModel edaResultModel = resultModelList.get(i);

            Map<String, Double> values = new HashMap<>();

            values.put("mean", edaResultModel.mean);
            values.put("variance", edaResultModel.variance);
            values.put("median", edaResultModel.median);

            columns.put(edaResultModel.name, values);
            System.out.println(edaResultModel.name);
//            columns.put("weight", values);
//            model.addAttribute("mean", resultModel.mean);
//            model.addAttribute("median", resultModel.median);
//            model.addAttribute("variance", resultModel.variance);
//
        }

        model.addAttribute("controlTaskId", controlTaskId );
        model.addAttribute("columns", columns);


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


    private List<EDAResultModel> getResults(String controlTaskId){
        
        /**
         * i want to retrieve ids which are controlled by ComputeTasksControllerDoc
         * for every such id i want the resultDoc from the db
         * the put the model to serve them to the frontend
         */
        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlCollection);
        System.out.println(doc);
        List<String> taskIds = doc.getTaskIds();
        List<EDAResultModel> resultModelList = new ArrayList<EDAResultModel>();

        for (int i = 0; i < taskIds.size(); i++) {
            String id = taskIds.get(i);
            DescriptiveStatisticsResultDoc descriptiveStatisticsResultDoc = mongoTemplate.findById(id,
                    DescriptiveStatisticsResultDoc.class, "compute_results_coll");
            EDAResultModel resultModel = descriptiveStatisticsResultDoc.getResultModel();

//            resultModel.name = descriptiveStatisticsResultDoc.getName();
//            resultModel.mean = descriptiveStatisticsResultDoc.getMean();
//            resultModel.median = descriptiveStatisticsResultDoc.getMedian();
//            resultModel.variance = descriptiveStatisticsResultDoc.getVariance();
            resultModelList.add(resultModel);
        }


        return resultModelList ;
    }
}
