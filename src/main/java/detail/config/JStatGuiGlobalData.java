package detail.config;

import detail.datasets.IDataSetContainer;

import detail.tasks.TaskBase;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

public class JStatGuiGlobalData {

    /**
     * The container used to hold the loaded data sets
     */
    public static IDataSetContainer dataSetContainer;


    /**
     * A set of worker pools used around
     */
    public static ThreadPoolTaskExecutor workersPool;

    /**
     * Tasks submitted for computation
     */
    public static List<TaskBase> tasks;

    public static boolean hasTask(String name){
        boolean found = false;

        for(int i=0; i<tasks.size(); ++i ){
            if(tasks.get(i).getTaskName().equals(name)){
                found = true;
                break;
            }
        }

        return found;
    }

    public static TaskBase getTask(String name){
        boolean found = false;

        for(int i=0; i<tasks.size(); ++i ){
            if(tasks.get(i).getTaskName().equals(name)){
                return tasks.get(i);
            }
        }

        return null;
    }

}
