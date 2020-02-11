package mongodb.mocks;

import mongodb.DocBase;
import org.springframework.data.mongodb.core.query.Query;

public interface IMongoDBCollectionMock {

    /**
     * Add a new object in the Db
     */
    <T extends DocBase> void add(T object);

    /**
     * Find one document that satisfies the criteria
     */
    <T extends DocBase> T findOne(Query query , Class<T> objClass);

}
