package gui.controllers.elasticsearch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/elasticsearch")
public class ElasticLoadDataController {

    /**
     * Returns the view of the form where the user can load
     * data from a query performed on the specified ElasticSearch DB
     * @return
     */
    @GetMapping
    public String getView(){

        return "elastic_view";
    }

    /**
     * Handles the form submission with the user query
     * on the ElasticSearch DB
     * @return
     */
    @PostMapping
    public String handlePostForm(){

        return null;
    }
}
