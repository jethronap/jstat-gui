package mongodb;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.awt.*;
import java.util.Map;

/**
 * General interface for all MongoDB documents
 */
public interface IDoc {


    /**
     * Return the id of the document
     */
    String getId();

    /**
     * Return the ObjectId
     */
    ObjectId getObjectId();

    /**
     * Set the id of the document
     */
    void setId(String id);

    /**
     * Save the document in the provided MongoDB instance
     */
    void save(MongoTemplate db);

    /**
     * Update the document in the provided MongoDB instance
     */
    void update(MongoTemplate db);

    /**
     * Returns the collection name the document belongs to
     */
    String getCollectionName();

    /**
     * Helper class used for mocking. Returns true if the
     * document satisfies all the conditions described by the Map
     * instance.
     */
    boolean satisfiesConditions(Map<String, String> conditions);


}
