package detail.tasks;

import detail.JStateMessage;
import detail.compute.DescriptiveStatistics;
import detail.datasets.IDataSet;
import detail.datasets.IDataSetContainer;
import detail.datasets.TableDataSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Compute the descriptive statistics of the columns from the given
 * data set
 */
public class ComputeDescriptiveStatisticsTask extends TaskBase {

    public ComputeDescriptiveStatisticsTask(String name, IDataSet dataSet, String... names){

        super(name);
        this.names = names;
        this.dataSet = dataSet;
    }


    @Override
    public JStateMessage call()throws Exception{

        this.setState(State.STARTED);

        stats = new ArrayList<>();
        try {
            for (String name : this.names) {

                System.out.println("Compute statistics for: " + name);
                DescriptiveStatistics statistics = new DescriptiveStatistics();
                statistics.setDataSetName(this.dataSet.getName());
                statistics.compute(this.dataSet.getItem(name));
                stats.add(statistics);
            }
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
    private IDataSet dataSet;


    /**
     * The names of the columns to work on
     */
    private String[] names;

    /**
     * Map that holds the statistics for every column
     */
    List<DescriptiveStatistics> stats;

}
