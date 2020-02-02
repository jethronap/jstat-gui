package detail.compute;

import detail.compute.NumericSample;
import detail.datasets.IDataSet;
import detail.datasets.TableDataSet;
import detail.wrappers.StatisticsWrapper;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import tech.tablesaw.columns.Column;

public class DescriptiveStats {

    public String name;
    public StatisticsWrapper statisticsWrapper;
    //public IDataSet dataSet;
    //public Column sample;

    public void compute(NumericSample sample) {

        sample.getStatistics();
        //this.sample = sample;
        //sample = new NumericSample(dataSet.getName(), 0);
//        DescriptiveStatistics statistics = new DescriptiveStatistics();
//        this.statisticsWrapper.mean = statistics.getMean();
//        this.statisticsWrapper.variance = statistics.getVariance();
//        this.statisticsWrapper.median = new Median().evaluate();


  //      sample.getStatistics();
    }
}
