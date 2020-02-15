package gui;

import detail.wrappers.FileWrapper;
import detail.tasks.LoadDatatSetTask;
import detail.config.JStatGuiGlobalData;
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

        // these should be logged better...
        /*System.out.println("================");
        System.out.println("handleDataSet...");
        System.out.println("================");
        System.out.println("Filename selected: "+fileName.fileName);*/

        // now properly from the file
        File file = new File("src/main/resources/datasets/" + fileName.fileName);

        // submit it to the pool
        JStatGuiGlobalData.workersPool.submit(new LoadDatatSetTask(file, JStatGuiGlobalData.dataSetContainer));

        // redirect to the index page
        return "redirect:/";
    }
}
