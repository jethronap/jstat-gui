package gui.controllers;

import detail.config.JStatGuiGlobalData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * QuitController the JStat-GUI
 * It shutdowns various utilities that have been started by the application
 */
@Controller
@RequestMapping("/quit")
public class QuitController {

    @GetMapping
    public String quitView(){
        return "quit_view";
    }

    @PostMapping
    public String quitPost(){


        System.out.println("================");
        System.out.println("quitPost...");
        System.out.println("================");

        // submit it to the pool
        JStatGuiGlobalData.workersPool.shutdown();

        // redirect to the index page
        return "redirect:/";
    }
}
