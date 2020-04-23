package gui.controllers;


import detail.models.EDAResultModel;

import gui.wrappers.AnalysisFormWrapper;

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

        if (doc == null) {
            throw new IllegalArgumentException("No control task for id: " + controlTaskId);
        }

        // if we haven't finished return
        if (!doc.isFinished()) {
            // msg to hit f5 prompt user to refresh somehow.
            model.addAttribute("please press f5 to view results");
            return "eda_view_results";
        }


        model.addAttribute("controlTaskId", controlTaskId);

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
        }

        model.addAttribute("columns", columns);


        return "eda_view_results";
    }

    // TODO: probably mundane & should be removed
    @PostMapping
    public String handleAnalysisResultForm(@RequestParam("controlTaskId") String controlTaskId, AnalysisFormWrapper formWrapper /*Errors errors*/) throws Exception {


        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlCollection);

        if (doc == null) {
            throw new IllegalArgumentException("No control task for id: " + controlTaskId);
        }
        return "redirect:/eda_view_results?controlTaskId=" + controlTaskId;
    }


    /**
     * @param controlTaskId
     * @return a list of results for every id.
     * helper method to serve the results from the db to the frontend.
     */
    private List<EDAResultModel> getResults(String controlTaskId) {

        ComputeTasksControllerDoc doc = mongoTemplate.findById(controlTaskId, ComputeTasksControllerDoc.class, controlCollection);
        System.out.println(doc);
        List<String> taskIds = doc.getTaskIds();
        List<EDAResultModel> resultModelList = new ArrayList<EDAResultModel>();

        for (int i = 0; i < taskIds.size(); i++) {
            String id = taskIds.get(i);
            DescriptiveStatisticsResultDoc descriptiveStatisticsResultDoc = mongoTemplate.findById(id,
                    DescriptiveStatisticsResultDoc.class, "compute_results_coll");
            EDAResultModel resultModel = descriptiveStatisticsResultDoc.getResultModel();

            resultModelList.add(resultModel);
        }

        return resultModelList;
    }
}
