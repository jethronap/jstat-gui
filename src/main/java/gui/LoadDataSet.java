package gui;

import detail.FileWrapper;
import detail.LoadDatatSetTask;
import detail.JStatGuiGlobalData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping("/load-dataset")
public class LoadDataSet {

    @GetMapping
    public String loadDataSet(Model model){

        model.addAttribute("fileName", new FileWrapper());
        return "load_dataset";
    }

    @PostMapping
    public String handleDataSet(@Valid FileWrapper fileName, Errors errors){

        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "/load-dataset";
        }


        System.out.println("================");
        System.out.println("handleDataSet...");
        System.out.println("================");
        System.out.println("Filename selected: "+fileName.fileName);

        // now properly from the file
        File file = new File("resources/datasets" + fileName.fileName);

        // create the task that loads the data set
        LoadDatatSetTask task = new LoadDatatSetTask(file, JStatGuiGlobalData.dataSetContainer);

        //System.out.println("Number of threads used: "+JStatGuiGlobalData.workersPool.);

        // submit it to the pool
        JStatGuiGlobalData.workersPool.submit(task);

        // redirect to the index page
        return "redirect:/";
    }
}
