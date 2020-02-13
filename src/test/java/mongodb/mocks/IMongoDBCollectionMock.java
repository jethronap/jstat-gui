package mongodb.mocks;

import mongodb.IDoc;
import org.springframework.data.mongodb.core.query.Query;

public interface IMongoDBCollectionMock {

    /**
     * Add a new object in the Db
     */
    <T extends IDoc> void add(T object);

    /**
     * Find one document that satisfies the criteria
     */
    <T extends IDoc> T findOne(Query query , Class<T> objClass);

}
