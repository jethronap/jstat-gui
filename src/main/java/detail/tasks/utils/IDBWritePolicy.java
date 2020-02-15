package detail.tasks.utils;

import detail.tasks.TaskBase;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public interface IDBWritePolicy {

    /**
     * Take the given task and write it to the DB
     */
    void write(TaskBase task, MongoTemplate mongoTemplate);

    /**
     * Take the list of tasks and write it to the DB
     */
    default void write(List<? extends TaskBase> tasks, MongoTemplate mongoTemplate){

        if(tasks == null){
            throw new IllegalArgumentException("Null tasks list given");
        }

        if(mongoTemplate == null){
            throw new IllegalArgumentException("MongoDB accessor is NULL");
        }

        for(int t=0; t<tasks.size(); ++t){
            this.write(tasks.get(t), mongoTemplate);
        }
    }
}
