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

    /**
      * Returns the mean
     */
    public double getMean(){
        return this.model.mean;
    }

    /**
     * Returns the median
     */
    public double getMedian(){
        return this.model.median;
    }

    /**
     * Returns the variance
     */
    public double getVariance(){
        return this.model.variance;
    }

    /**
     * Helper class used for mocking
     */
    @Override
    public boolean satisfiesConditions(Map<String, String> conditions){
        boolean satisfies = true;

        for (Map.Entry<String,String> entry : conditions.entrySet()) {

            String value = entry.getValue();
            String key = entry.getKey();

            if(key.equals("description")){
                satisfies = value.equals(this.description);
            }
            else{

                String[] modelKeys = key.split("[.]");
                key = modelKeys[1];

                if(key.equals("mean")){

                    double mean = Double.valueOf(value);
                    satisfies = (mean == model.mean);
                }
                else if(key.equals("variance")){
                    double variance = Double.valueOf(value);
                    satisfies = (variance == model.variance);
                }
                else if(key.equals("median")){
                    double median = Double.valueOf(value);
                    satisfies = (median == model.median);
                }
                else if(key.equals("name")){
                    satisfies = model.name.equals(value);
                }
            }

            if(!satisfies){
                break;
            }

        }

        return satisfies;
    }

    private String description;
    private EDAResultModel model;

    @Value("${compute_results_coll}")
    String collection_name;
}
