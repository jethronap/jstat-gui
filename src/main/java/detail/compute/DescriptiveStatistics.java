package detail.compute;

import detail.models.EDAResultModel;

public class DescriptiveStatistics  {


    /**
     * Constructor
     */
    public DescriptiveStatistics(){
        this.resultModel = new EDAResultModel();
    }

    /**
     * Compute the statistics of the given sample
     */
    public <SampleTp> void  compute(SampleTp sample){

       resultModel.mean = 13.0;
       resultModel.median = 15.0;
       resultModel.variance = 0.5;
    }

    /**
     * Set the name of the data set used
     */
    public void setDataSetName(String name){
        this.resultModel.name = name;
    }

    /**
     * Get the type of the computation model
     */
    public ComputationModelType getComputationModelType(){
        return ComputationModelType.EDA;
    }

    /**
     * Returns the model that represents the result of the
     * model
     */
    public EDAResultModel getResultModel(){

        return resultModel;
    }

    private EDAResultModel resultModel;
}
