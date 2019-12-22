package gui;

import detail.compute.DescriptiveStatistics;
import detail.config.JStatGuiGlobalData;
import detail.datasets.DataSetViewInfoHolder;
import detail.datasets.IDataSet;
import detail.wrappers.AnalysisFormWrapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analysis")
public class Analysis {


    @GetMapping
    public String analysisView(Model model){

        // in order to do an analysis we need the list of the loaded data sets
        List<String> dataSetNames = JStatGuiGlobalData.dataSetContainer.dataSetNames();

        if(dataSetNames.size() == 0){
            dataSetNames.add("No datasets loaded");
        }

        List<DataSetViewInfoHolder> dataSets = new ArrayList<>();

        for(int i=0; i<dataSetNames.size(); ++i){

            IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetNames.get(i));

            List<String> cols = new ArrayList<>();
            cols.add("All");

            List<String> dataSetCols = dataSet.getColumnNames();

            for(int name=0; name<dataSetCols.size(); ++name){
                cols.add(dataSetCols.get(name));
            }

            DataSetViewInfoHolder holder = new DataSetViewInfoHolder();
            holder.name = dataSet.getName();
            holder.names = cols;

            dataSets.add(holder);
        }

        model.addAttribute("dataSets", dataSets);

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
        System.out.println("Column Name: " + formWrapper.colName);

        // get the name of the dataset
        String dataSetName = formWrapper.dataSetName;
        IDataSet dataSet = JStatGuiGlobalData.dataSetContainer.getDataSet(dataSetName);

        if(formWrapper.colName == "All"){

            
        }
        else{

            /// just the column requested
            DescriptiveStatistics statistics = DescriptiveStatistics.compute(dataSet.getItem(formWrapper.colName));
        }
    }
}
