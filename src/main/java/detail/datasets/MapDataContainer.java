package detail.datasets;

import detail.datasets.IDataSet;
import detail.datasets.IDataSetContainer;

import java.util.*;

public class MapDataContainer implements IDataSetContainer {

    /**
     * Constructor
     */
    public MapDataContainer(){
        this.dataSetMap = new HashMap<>();
    }

    /**
     * Returns the Data set labelled with the given String
     * @param name the name of the data set in the container
     * @return IDataSet
     */
    @Override
    public IDataSet getDataSet(String name){
        return this.dataSetMap.get(name);
    }

    /**
     * Returns the number of data sets in the container
     */
    @Override
    public int nDataSets(){
        return this.dataSetMap.size();
    }

    /**
     * Add a new data set in the container
     */
    @Override
    public synchronized boolean  addDataSet(IDataSet dataSet){

        // we wan this to be synchronized...
        // TODO: A task is reading a data set that already exists...
        // then we should not add...
        System.out.println("Adding dataSet "+dataSet.getName());

        // the dataset already exists so nothing to do
        if(this.dataSetMap.containsKey(dataSet.getName())) {
            return true;
        }

        this.dataSetMap.put(dataSet.getName(), dataSet);
        System.out.println("Added dataSet "+dataSet.getName());
        return true;
    }

    /**
     * Returns  a list of the loaded dataset names
     */
    @Override
    public List<String> dataSetNames(){
        return new ArrayList<>(this.dataSetMap.keySet());
    }


    /**
     * Clear whatever the data set  may contain
     */
    @Override
    public void clear(){
        dataSetMap.clear();
    }


    private Map<String, IDataSet> dataSetMap;

}
