package detail.compute;


import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class DescriptiveStats {

    public String name;
    public double mean;
    public double variance;
    public double median;

    public <SampleTp> void  compute(SampleTp sample){

        DescriptiveStatistics statistics = new DescriptiveStatistics();

        //statistics.addValue(sample);
//        this.mean = statistics.getMean();
//        this.variance = statistics.getVariance();
//        this.median = new Median().evaluate();
        //mean = statistics.getMean();
    }
}
