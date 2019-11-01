package gui;

import detail.FileWrapper;
import detail.IDataSet;
import detail.JStatGuiGlobalData;
import detail.LoadDatatSetTask;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping("/quit")
public class Analysis {


    @GetMapping
    public String analysisView(){

        // in order to do an analysis we need the list
        // of datasets loaded
        return "analysis_index";
    }

    @PostMapping
    public String exploreDataSet(@Valid FileWrapper fileName, Errors errors){

        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "/load-dataset";
        }


        System.out.println("================");
        System.out.println("exploreDataSet...");
        System.out.println("================");
        System.out.println("Filename exploreDataSet: "+fileName.fileName);

        // get the name of the dataset
        String datasetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(datasetName);

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
