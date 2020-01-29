package detail.tasks;
import detail.JStateMessage;
import detail.tasks.utils.IDBWritePolicy;
import detail.tasks.utils.TaskUtils;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.util.List;

/**
 * Monitors the progress of tasks that have been assigned
 * to the controller
 */
public class ComputeTasksController extends TaskBase {

    /// constructor
    public ComputeTasksController(String name, ThreadPoolTaskExecutor executor,
                                  List<? extends TaskBase> tasks, IDBWritePolicy writePolicy){

        super(name);
        this.executor=executor;
        this.tasks=tasks;
        this.writePolicy = writePolicy;
    }

    @Override
    public JStateMessage call()throws Exception{

        this.setState(State.STARTED);

        // submit tasks to the executor
        for(int t=0; t<tasks.size(); ++t){
            executor.submit(tasks.get(t));
        }

        // yield until they are finished
        while(TaskUtils.finished(tasks) == false){
            Thread.yield();
        }

        // tasks are done collect the result and
        // write the result to the DB
        writePolicy.write(tasks);

        // no notify the Messaging that
        msg = new JStateMessage("Statistics computation for  is finished");
        this.setState(State.FINISHED);

        return msg;
    }


    private ThreadPoolTaskExecutor executor;
    private List<? extends TaskBase> tasks;
    private IDBWritePolicy writePolicy;

}
