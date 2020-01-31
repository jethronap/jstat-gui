package detail.compute.utils;

public class Statistics {

    public double mean = 0.0;
    public double variance = 0.0;
    public double median = 0.0;


    public final void printInfo() {
        System.out.println(this.toString());
    }

    @Override
    public final String toString() {
        String str = "";

        str += "Mean: " + mean + "\n";
        str += "Variance: " + variance + "\n";
        str += "Median: " + median + "\n";

        return str;
    }
}
