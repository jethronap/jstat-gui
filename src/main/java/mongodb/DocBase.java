package mongodb;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.awt.*;
import java.util.Map;

/**
 * Base class for all MongoDB documents
 */
public abstract class DocBase implements IDefaultBuildable{


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
     * Helper class used for mocking
     */
    public abstract boolean satisfiesConditions(Map<String, String> conditions);

    /**
     * Id of the document
     */
    @Id
    private String id;


}
