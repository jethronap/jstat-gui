package detail.compute;

import detail.wrappers.StatisticsWrapper;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumericSample {

    protected StatisticsWrapper statisticsWrapper;
    protected String name;
    protected List<Double> data = null;


    /**
     * Constructor
     */
    public NumericSample(String name, int capacity) {

        this.statisticsWrapper = new StatisticsWrapper();
        this.name = name;

        if (capacity == 0) {
            this.data = new ArrayList<Double>();
        }
        else {
            this.initialize(capacity);
        }
    }

    /**
     * Constructor
     */
    public NumericSample(String name, List<Double> data) {

        this(name, data.size());
        copy(data);
    }


    /**
     * The name of the sample
     */
    public String name() {
        return this.name;
    }


    /**
     * Returns the size of the sample
     */
    public int size() {
        return this.data.size();
    }


    /**
     * Returns the held data as an array
     */
    public final double[] asArray() {

        double[] data = new double[this.data.size()];

        for (int i = 0; i < this.data.size(); i++) {

            data[i] = this.data.get(i);

        }

        return data;
    }


    /**
     * Compute the mean of the sample
     */
    public final double getMean() {
        return getStatistics().mean;
    }


    /**
     * Compute the variance of the sample
     */
    public final double getVar() {
        return getStatistics().variance;
    }


    /**
     * Returns the median of the sample
     */
    public final double getMedian() {
        return getStatistics().median;
    }


    /**
     * Compute the statistics of the sample
     */
    public final StatisticsWrapper getStatistics() {

        compute_sample_statistics();

        return this.statisticsWrapper;
    }

    /**
     * Add the value to the sample
     */
    public final void add(Double value) {

        this.data.add(value);
    }


    /**
     * Set the i-th entry to the given value
     */
    public final void set(int i, Double value) {

        this.data.set(i, value);
    }


    /**
     * Returns the i-th entry of the sample
     */
    public final Double get(int i) {
        return this.data.get(i);
    }


    /**
     * Copy the data from the given list
     */
    public void copy(final List<Double> data) {


        if (data.size() == 0) {
            throw new IllegalStateException("The input data set has zero size");
        }

        if (this.data.size() != data.size()) {

            // remove data completely
            this.initialize(data.size());
        }

        Collections.copy(this.data, data);

    }

    /**
     * Initialize the sample with zero entries
     */
    protected final void initialize(int size) {

        this.data = new ArrayList<Double>(size);

        for (int i = 0; i < size; ++i) {
            this.data.add(0.0);
        }
    }


    protected void compute_sample_statistics() {

        double[] arrayData = this.asArray();
        DescriptiveStatistics statistics = new DescriptiveStatistics();

        this.statisticsWrapper.mean = statistics.getMean();
        this.statisticsWrapper.variance = statistics.getVariance();
        this.statisticsWrapper.mean = new Median().evaluate(arrayData);
    }
}
