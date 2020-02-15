package mongodb.mocks;

import org.springframework.beans.factory.annotation.Value;

public class MongoDBCollectionMockFactory {

    //@Value("${collections.compute.results}")
    static String compute_results_collection = "compute_results_coll";

    public static IMongoDBCollectionMock build(String name){

        if(name.equals(compute_results_collection)){
            return new ComputeResultsCollectionMock();
        }

        throw new IllegalArgumentException("No collection named: "+name);
    }
}
