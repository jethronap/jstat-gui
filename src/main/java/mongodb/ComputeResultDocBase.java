package mongodb;

import detail.compute.ComputationModelType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Abstract class that represents a document that modesl
 * a computation result
 */
public abstract class ComputeResultDocBase implements IDoc {


    /**
     * Save the document in the provided MongoDB instance
     */
    public abstract void save(MongoTemplate db);

    /**
     * Returns the collection name the document belongs to
     */
    public abstract String getCollectionName();

    /**
     * Return the id of the document
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Return the ObjectId
     */
    @Override
    public ObjectId getObjectId(){return new ObjectId(this.getId());}

    /**
     * Set the id of the document
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the computational model type
     * @return
     */
    public ComputationModelType getComputationModelType() {
        return computationModelType;
    }

    /**
     * Set the computational model type
     */
    protected void setComputationModelType(ComputationModelType computationModelType) {
        this.computationModelType = computationModelType;
    }

    /**
     * Id of the document
     */
    @Id
    private String id;

    /**
     * The type of the result
     */
    ComputationModelType computationModelType;
}
