package detail.compute;


import detail.wrappers.StatisticsWrapper;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class DescriptiveStats {

    public String name;
    public StatisticsWrapper statisticsWrapper;

    public <SampleTp> void compute(SampleTp sample) {

        DescriptiveStatistics statistics = new DescriptiveStatistics();

        this.statisticsWrapper.mean = statistics.getMean();
        this.statisticsWrapper.variance = statistics.getVariance();
        //  this.median = new Median().evaluate();


    }
}
