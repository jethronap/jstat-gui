package detail.compute;

public class DescriptiveStatistics implements IComputeModel {


    /**
     * Constructor
     */
    public DescriptiveStatistics(){
        this.resultModel = new EDAResultModel();
    }

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
    @Override
    public ComputationModelType getComputationModelType(){
        return ComputationModelType.EDA;
    }


    /**
     * Returns the model that represents the result of the
     * model
     */
    @Override
    public IResultModel getResultModel(){

        return resultModel;
    }

    private EDAResultModel resultModel;
}
