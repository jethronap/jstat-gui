package gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/load-dataset")
public class GetLoadDataSet {

    @GetMapping
    public String loadDataSet(){
        return "load_dataset";
    }
}
