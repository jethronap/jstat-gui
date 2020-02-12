package mongodb;

import detail.models.EDAResultModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    /**
     * Save the document in the provided MongoDB instance
     */
    public void save(MongoTemplate db){

        if(db == null){
            throw new IllegalArgumentException("MongoDB instance given is null");
        }

        db.save(this, this.collection_name);
    }

    /**
     * Returns the collection name the document belongs to
     */
    public String getCollectionName(){
        return collection_name;
    }


    /**
     * Returns the name of the model
     */
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

    @Value("${compute_results_coll}")
    String collection_name;
}
