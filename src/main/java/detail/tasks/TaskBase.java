package detail.tasks;

import detail.JStateMessage;

import java.util.concurrent.Callable;

public abstract class TaskBase implements Callable<JStateMessage> {

    /**
     * Enumeration to express the state of the task
     */
    public enum State{PENDING, FINISHED, STARTED, INTERRUPTED};


    public void setMsg(JStateMessage msg){
        this.msg = msg;
    }



    public JStateMessage getMsg() {
        return msg;
    }

    /**
     * Get the state
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Reschedule the task
     */
    public void rescedule(){
        this.setState(State.PENDING);
    }

    /**
     * Returns true if the task is finished
     */
    public boolean finished(){
        return this.state == State.FINISHED;
    }


    public void setTaskName(String name){

        this.name = name;
    }

    public String getTaskName(){
        return this.name;
    }

    /**
     * Constructor
     */
    protected TaskBase(String name){
        this.state = State.PENDING;
        this.name = name;
    }

    /**
     * Message to the client code
     */
    protected JStateMessage msg;

    /**
     * Flag indicating the state of the task
     */
    State state;

    /**
     * Set the task name
     */
    String name;
}
