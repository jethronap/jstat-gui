package detail.tasks.utils;

import detail.tasks.TaskBase;

import java.util.List;

public class TaskUtils {

    /**
     * Returns true if all tasks in the given list have state FINISHED
     * @param tasks
     * @return
     */
    public static boolean finished(List<? extends TaskBase> tasks){

        if(tasks == null){
            throw new IllegalArgumentException("null tasks list given");
        }

        boolean result = true;
        for(int t=0; t<tasks.size(); ++t){

            if(tasks.get(t).getState() != TaskBase.State.FINISHED){
                result = false;
                break;
            }
        }

        return result;
    }
}
