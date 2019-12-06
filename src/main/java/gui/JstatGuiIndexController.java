package gui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;


/**
 * Handles the index or else main screen of JStat
 */

@Controller
public class JstatGuiIndexController {

    private String appMode;

    @Autowired
    public JstatGuiIndexController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "User");
        model.addAttribute("projectname", "JstatGUI");

        model.addAttribute("mode", appMode);

        return "index";
    }

}
