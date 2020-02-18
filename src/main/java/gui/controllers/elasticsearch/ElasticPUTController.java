package gui.controllers.elasticsearch;

import detail.utils.StringUtils;
import gui.wrappers.ElasticPUTWrapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/elasticsearch-put")
public class ElasticPUTController {

    @Autowired
    RestHighLevelClient highLevelClient;

    /**
     * Handles the view of the form for adding data in ElasticSearch
     */
    @GetMapping
    public String getView(Model model){
        model.addAttribute("elasticPutForm", new ElasticPUTWrapper());
        return "elastic_put_view";
    }

    /**
     * Handles the view of the form for adding data in ElasticSearch
     */
    @PostMapping
    public String handlePostForm(@Valid ElasticPUTWrapper elasticPutForm, Errors errors){

        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "elastic_put_view";
        }

        if(highLevelClient == null){
            throw new NullPointerException("RestHighLevelClient not initialized????");
        }

        RestClient lowLevel = highLevelClient.getLowLevelClient();

        String index = elasticPutForm.indexName;
        String type = elasticPutForm.typeName;
        String jsonInputText =  elasticPutForm.jsonInputText;

        System.out.println("Input given: " + jsonInputText);

        IndexRequest indexRequest;
        IndexResponse response;
        Map<String, Object> map = StringUtils.jsonStringToMap(jsonInputText);

        if(elasticPutForm.docId == null){

            //we do a PUT
            indexRequest = new IndexRequest(index)
                    .type(type)
                    .source(map);
        }
        else{

            indexRequest = new IndexRequest(index)
                    .id(elasticPutForm.docId)
                    .type(type)
                    .source(map);
        }
        try {
            // index the document
            response = highLevelClient.index(indexRequest);

            log.info("Elastic data submitted: " + elasticPutForm);
            log.info("Add Elasticsearch doc: " + response.getId());
        }
        catch(IOException exception){
            log.error("An exception occurred whilst inserting document in Elasticsearch");
        }


        return "redirect:/analysis";
    }
}
