package detail.compute;

import detail.models.EDAResultModel;
import org.apache.commons.math3.stat.StatUtils;
import stats.Statistics;

public class DescriptiveStatistics {


    /**
     * Constructor
     */
    public DescriptiveStatistics() {
        this.resultModel = new EDAResultModel();
    }

    /**
     * Compute the statistics of the given sample
     */
    public <SampleTp> void compute(SampleTp sample) {

        double[] array = {23, 32, 5, 6, 7, 7.8, 9, 34, 22, 77};
        org.apache.commons.math3.stat.descriptive.DescriptiveStatistics stats = new org.apache.commons.math3.stat.descriptive.DescriptiveStatistics();

        for( int i = 0; i < array.length; i++) {
            stats.addValue(array[i]);
        }
        this.resultModel.mean = stats.getMean();
        this.resultModel.median = stats.getPercentile(50);
        this.resultModel.variance = stats.getVariance();

//        this.resultModel.mean = StatUtils.mean(array);
//        this.resultModel.median = StatUtils.percentile(array, 50);
//        this.resultModel.variance = StatUtils.variance(array);
    }

    /**
     * Set the name of the data set used
     */
    public void setDataSetName(String name) {
        this.resultModel.name = name;
    }

    /**
     * Get the type of the computation model
     */
    public ComputationModelType getComputationModelType() {
        return ComputationModelType.EDA;
    }

    /**
     * Returns the model that represents the result of the
     * model
     */
    public EDAResultModel getResultModel() {
        return resultModel;
    }

    private EDAResultModel resultModel;
}
