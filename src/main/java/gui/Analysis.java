package gui;

import detail.compute.DescriptiveStatistics;
import detail.config.JStatGuiGlobalData;
import detail.datasets.IDataSet;
import detail.wrappers.AnalysisFormWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/analysis")
public class Analysis {


    @GetMapping
    public String analysisView(Model model){

        //System.out.println("Loading dataset is still: "+JStatGuiGlobalData.taskFuture.toString());

        // in order to do an analysis we need the list
        // of datasets loaded
        List<String> dataSetNames = JStatGuiGlobalData.dataSetContainer.dataSetNames();

        if(dataSetNames.size() == 0){
            dataSetNames.add("No datasets loaded");
        }

        model.addAttribute("dataSetNames", dataSetNames);

        return "analysis_index";
    }

    @PostMapping
    public String handleAnalysisForm(@Valid AnalysisFormWrapper formWrapper, Errors errors){

        // validate form
        if (errors.hasErrors()) {
            System.out.println("Form has errors...");
            return "/analysis";
        }

        // compute descriptive statistics
        if(formWrapper.eda != null){

            this.computeDataSetStatistics(formWrapper);
        }

        // redirect to the analysis page again
        return "redirect:/analysis";
    }

    protected void computeDataSetStatistics(AnalysisFormWrapper formWrapper){


        System.out.println("================");
        System.out.println("computeDataSetStatistics...");
        System.out.println("================");
        System.out.println("Filename exploreDataSet: "+formWrapper.eda);
        System.out.println("Filename exploreDataSet: "+formWrapper.dataSetName);

        // get the name of the dataset
        String dataSetName = formWrapper.dataSetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetName);

        for(int row=0; row<dataSet.size(); ++row){

            DescriptiveStatistics statistics = DescriptiveStatistics.compute(dataSet.getItem(row));
        }

        // launch the task that does the analysis

        //// redirect to the analysis page again
        //return "redirect:/analysis";
    }
}
