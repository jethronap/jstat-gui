package detail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MapDataContainer implements IDataSetContainer {


    public MapDataContainer(){
        this.dataSetMap = new HashMap<>();
    }

    /**
     * Retruns the Data set labelled with the given String
     * @param name the name of the data set in the container
     * @return IDataSet
     */
    public IDataSet getDataSet(String name){
        return this.dataSetMap.get(name);
    }


    /**
     * Returns the number of data sets in the container
     */
    public int nDataSets(){
        return this.dataSetMap.size();
    }


    /**
     * Add a new data set in the container
     */
    @Override
    public boolean addDataSet(IDataSet dataSet){

        // we wan this to be synchronized...
        // TODO: A task is reading a data set that already exists...
        // then we should not add...
        synchronized (this) {
            this.dataSetMap.put(dataSet.getName(), dataSet);
            return true;
        }
    }


    private Map<String, IDataSet> dataSetMap;

}
