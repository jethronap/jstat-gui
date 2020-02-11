package mongodb.mocks;

import mongodb.DocBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;

public class MongoDBMock {

    //@Value("${collections.compute.results}")
    String compute_results_collection = "compute_results_coll";


    /**
     * Constructor initializes the collections
     */
    public MongoDBMock(){

        db_ = new HashMap<>();
        db_.put(compute_results_collection, MongoDBCollectionMockFactory.build(compute_results_collection));
    }

    public <T extends DocBase> T findOne(Query criteria, Class<T> objClass, String collection){

        if(collection == null){
            throw new IllegalArgumentException("Collection name not specified");
        }

        return db_.get(collection).findOne(criteria, objClass);
    }

    public <T extends DocBase> void save(T object, String collection){
        db_.get(collection).add(object);
    }


    private Map<String, IMongoDBCollectionMock> db_;
}
