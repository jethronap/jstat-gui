package mongodb;

import detail.compute.EDAResultModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * DescriptiveStatisticsResultDoc
 */
@Document(collection="compute-results")
public class DescriptiveStatisticsResultDoc extends ComputeResultDocBase {

    /**
     * Constructor
     */
    public DescriptiveStatisticsResultDoc(){

        this.model = null;
        this.description = "No Description";
    }

    /**
     * Construct the result using the EDAResultModel
     * @param model
     */
    public DescriptiveStatisticsResultDoc(EDAResultModel model){

        this.model = model;
        this.description = "No Description";
    }

    /**
     * Construct the result using the EDAResultModel
     * @param model
     */
    public DescriptiveStatisticsResultDoc(EDAResultModel model, String description){

        this.model = model;
        this.description = description;
    }

    @Override
    public IDefaultBuildable build(){
        return new DescriptiveStatisticsResultDoc();
    }

    public String getName(){
        return this.model.name;
    }

    public double getMean(){
        return this.model.mean;
    }

    public double getMedian(){
        return this.model.median;
    }

    public double getVariance(){
        return this.model.variance;
    }

    /**
     * Helper class used for mocking
     */
    @Override
    public boolean satisfiesConditions(Map<String, String> conditions){
        return true;
    }

    private String description;
    private EDAResultModel model;
}
