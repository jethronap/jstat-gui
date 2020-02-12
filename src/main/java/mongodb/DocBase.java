package mongodb;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.awt.*;
import java.util.Map;

/**
 * Base class for all MongoDB documents
 */
public abstract class DocBase {


    /**
     * Return the id of the document
     */
    public String getId() {
        return id;
    }

    /**
     * Return the ObjectId
     */
    public ObjectId getObjectId(){return new ObjectId(this.getId());}

    /**
     * Set the id of the document
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Save the document in the provided MongoDB instance
     */
    public abstract void save(MongoTemplate db);

    /**
     * Returns the collection name the document belongs to
     */
    public abstract String getCollectionName();

    /**
     * Helper class used for mocking. Returns true if the
     * document satisfies all the conditions described by the Map
     * instance.
     */
    public abstract boolean satisfiesConditions(Map<String, String> conditions);


    /**
     * Id of the document
     */
    @Id
    private String id;


}
