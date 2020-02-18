package gui.controllers.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path="/elasticsearch", produces="application/json")
public class ElasticGETRestController {

    @Autowired
    RestHighLevelClient highLevelClient;

    @GetMapping("/{index}/{type}/{id}")
    public ResponseEntity<Map<String, Object>> getView(@PathVariable String index, @PathVariable String type, @PathVariable String id){

        RestClient lowLevel = highLevelClient.getLowLevelClient();

        GetRequest request = new GetRequest(index, type, id);
        System.out.println("Request is: "+request.toString());
        try{
            GetResponse response = highLevelClient.get(request, RequestOptions.DEFAULT);
            Map<String, Object> properties = response.getSourceAsMap();
            return new ResponseEntity<>(properties, HttpStatus.OK);
        }
        catch(ElasticsearchException e){
            log.error("Elasticsearch Exception: " + e.getMessage() );
        }
        catch (Exception e){
            log.error("An exception occurred whilst retrieving document in Elasticsearch " + e.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



}
