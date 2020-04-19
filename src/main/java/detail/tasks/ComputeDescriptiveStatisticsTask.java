package detail.tasks;

import detail.JStateMessage;
import detail.compute.DescriptiveStatistics;
import detail.datasets.IDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Compute the descriptive statistics of the columns from the given
 * data set
 */
public class ComputeDescriptiveStatisticsTask<T> extends TaskBase {

    public ComputeDescriptiveStatisticsTask(String taskType, IDataSet dataSet, String name){

        super(taskType);
        this.colName = name;
        this.dataSet = dataSet;
    }


    @Override
    public JStateMessage call()throws Exception{

        this.setState(State.STARTED);

        stats = new ArrayList<>();

        try {

                System.out.println("Compute statistics for: " + this.colName);
                DescriptiveStatistics statistics = new DescriptiveStatistics();
                statistics.setDataSetName(this.colName);
                statistics.compute(this.dataSet.getItem(colName));
                stats.add(statistics);

        }
        catch(Exception e){
            System.out.print("An exception occured whilst computing");
            this.setState(State.FINISHED);
            throw e;
        }


        // no notify the Messaging that
        msg = new JStateMessage("Statistics computation for " + dataSet.getName()+" is finished");
        this.setState(State.FINISHED);
        System.out.println("Task Finished");

        return msg;
    }

    /**
     * Return the result of the task
     */

    public List<DescriptiveStatistics> getResult(){return this.stats;}


    /**
     * Return the result. It waits the specified amount of time
     * if the task is not finished
     */

    public List<DescriptiveStatistics> getResultOrWait(int time){


        if(!this.finished()){

            try {
                wait(time);
            }
            catch (InterruptedException e){

            }
        }

        return this.stats;
    }


    /**
     * Instance that holds the loaded data set
     */
    private IDataSet<T> dataSet;


    /**
     * The names of the columns to work on
     */

    //String name colName
    private String colName;

    /**
     * Map that holds the statistics for every column
     */
    List<DescriptiveStatistics> stats;

}
