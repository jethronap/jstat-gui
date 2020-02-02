package detail.tasks;

import detail.JStateMessage;
import detail.compute.DescriptiveStats;
import detail.compute.NumericSample;
import detail.datasets.IDataSet;

import java.util.ArrayList;
import java.util.List;

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
        for(String name : this.names){

            DescriptiveStats statistics = new DescriptiveStats();
            statistics.name = name;
            statistics.compute((NumericSample) this.dataSet.getItem(name));
            stats.add(statistics);
        }

        // no notify the Messaging that
        msg = new JStateMessage("Statistics computation for " + dataSet.getName()+" is finished");
        this.setState(State.FINISHED);

        return msg;
    }

    /**
     * Return the result of the task
     */
    List<DescriptiveStats> getResult(){return this.stats;}

    /**
     * Return the result. It waits the specified amount of time
     * if the task is not finished
     */
    List<DescriptiveStats> getResultOrWait(int time){

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
    List<DescriptiveStats> stats;

}
