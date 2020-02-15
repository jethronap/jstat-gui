package detail.tasks;
import detail.JStateMessage;
import detail.compute.ComputationModelType;
import detail.tasks.utils.IDBWritePolicy;
import detail.tasks.utils.TaskUtils;

import mongodb.ComputeTasksControllerDoc;
import mongodb.IDoc;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Monitors the progress of tasks that have been assigned
 * to the controller
 */
public class ComputeTasksController extends TaskBase {



    /// constructor
    public ComputeTasksController(ThreadPoolTaskExecutor executor,
                                  MongoTemplate mongoTemplate,
                                  List<? extends TaskBase> tasks,
                                  IDBWritePolicy writePolicy){

        super("ComputeTasksController");
        this.executor=executor;
        this.tasks=tasks;
        this.doc = new ComputeTasksControllerDoc();
        this.mongoTemplate = mongoTemplate;
        this.writePolicy = writePolicy;
    }

    /**
     * Save the controller in the DB
     */
    public void save(){

        if(mongoTemplate == null){
            throw new IllegalStateException("MongoDB accessor is NULL");
        }

        doc.save(mongoTemplate);
    }

    /**
     * Return the id of the document
     */
    public String getId() {
        return doc.getId();
    }

    /**
     * Return the ObjectId
     */
    public ObjectId getObjectId(){return doc.getObjectId();}


    /**
     * Returns the computational model type
     * @return
     */
    public ComputationModelType getComputationModelType() {
        return doc.getComputationModelType();
    }

    /**
     * Set the computational model type
     */
    public void setComputationModelType(ComputationModelType computationModelType) {
        this.doc.setComputationModelType(computationModelType);
    }


    @Override
    public JStateMessage call()throws Exception{

        System.out.println("Control task: "+this.getId() + " is started");
        this.setState(TaskBase.State.STARTED);

        if(mongoTemplate == null){
            System.out.println("MongoDB template is null");

            // no notify the Messaging that
            msg = new JStateMessage("Computation cannot be executed with a null MongoDB");
            this.setState(State.FINISHED);
            return msg;
        }

        // submit tasks to the executor
        for(int t=0; t<tasks.size(); ++t){
            executor.submit(tasks.get(t));
        }

        while(TaskUtils.finished(this.tasks) == false){
            // sleep for some time. Thread.yield()
            // seems to block the whole thing
            //Thread.yield();
            TimeUnit.SECONDS.sleep(1);
        }


        // tasks are done. Persist them in the
        // DB so that we get unique ids for them
        // so that we can query them
        writePolicy.write(tasks, mongoTemplate);

        List<String> taskIds = new ArrayList<>();

        for(int t=0; t<this.tasks.size(); ++t){
            taskIds.add(this.tasks.get(t).getId());
        }

        this.doc.setTaskIds(taskIds);
        this.doc.setFinished(true);
        this.doc.save(mongoTemplate);
        System.out.println("Finished Control task: "+this.getId());

        // no notify the Messaging that
        msg = new JStateMessage("Statistics computation for  is finished");
        this.setState(State.FINISHED);
        return msg;
    }


    private ThreadPoolTaskExecutor executor;
    private List<? extends TaskBase> tasks;
    private IDBWritePolicy writePolicy;
    private MongoTemplate mongoTemplate;

    /// The document that this controller is mapped
    /// to the db
    private ComputeTasksControllerDoc doc;

}
