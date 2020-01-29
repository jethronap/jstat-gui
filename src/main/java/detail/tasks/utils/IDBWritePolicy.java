package detail.tasks.utils;

import detail.tasks.TaskBase;

import java.util.List;

public interface IDBWritePolicy {

    /**
     * Take the given task and write it to the DB
     */
    void write(TaskBase task);

    /**
     * Take the list of tasks and write it to the DB
     */
    default void write(List<? extends TaskBase> tasks){

        if(tasks == null){
            throw new IllegalArgumentException("Null tasks list given");
        }

        for(int t=0; t<tasks.size(); ++t){
            this.write(tasks.get(t));
        }
    }
}
