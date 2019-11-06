package gui;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



/**
 * Handles the index or else main screen of JStat
 */

@Controller
public class JstatGuiIndex {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
