package detail.models;

import mongodb.DescriptiveStatisticsResultDoc;

public class EDAResultModel
{

    public String name;
    public double mean;
    public double variance;
    public double median;

    public static EDAResultModel buildFrom(DescriptiveStatisticsResultDoc doc){

        if(doc == null){
            throw new IllegalArgumentException("NULL DescriptiveStatisticsResultDoc document was given");
        }

        EDAResultModel result = new EDAResultModel();

        result.name = doc.getName();
        result.mean = doc.getMean();
        result.median = doc.getMedian();
        result.variance = doc.getVariance();
        return result;
    }
}
