package mongodb;

import detail.compute.ComputationModelType;
import detail.config.JStatGuiGlobalData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document(collection="control_tasks_coll")
public class ComputeTasksControllerDoc implements IDoc {


    public ComputeTasksControllerDoc(){
        this.finished = false;
        this.collection_name = JStatGuiGlobalData.control_tasks_collection;
    }

    /**
     * Save the document in the provided MongoDB instance
     */
    public void save(MongoTemplate db){

        if(db == null){
            throw new IllegalArgumentException("MongoDB instance is NULL");
        }

        db.save(this, this.collection_name);
    }

    @Override
    public void update(MongoTemplate db){

        //db.update(this);
    }

    /**
     * Returns the collection name the document belongs to
     */
    public String getCollectionName(){
        return collection_name;
    }

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
     * Helper class used for mocking. Returns true if the
     * document satisfies all the conditions described by the Map
     * instance.
     */
    @Override
    public boolean satisfiesConditions(Map<String, String> conditions){return true;}

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
    public void setComputationModelType(ComputationModelType computationModelType) {
        this.computationModelType = computationModelType;
    }

    /**
     * Return the task ids associated with this controller
     */
    public List<String> getTaskIds() {
        return taskIds;
    }

    /**
     * Set the task ids associated with this controller
     */
    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }

    /**
     * Brief query if the  task has finished
     * @return
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Brief set the finished flag
     * @param finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Id of the document
     */
    @Id
    private String id;

    /**
     * the collection name
     */
    @Value("${collections.control.tasks}")
    private String collection_name;

    /**
     * flag indicating if the controller finished
     */
    @Field
    private boolean finished;

    /**
     * The type of the result
     */
    @Field
    private ComputationModelType computationModelType;

    /**
     * The mongodb ids of tasks  that this controller controls
     */
    @Field
    private List<String> taskIds;
}
