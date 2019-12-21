package detail.datasets;


import detail.datasets.IDataSet;

import java.util.List;

/**
 * Minimal interface for containers that hold data sets
 */
public interface IDataSetContainer {

    /**
     * Retruns the Data set labelled with the given String
     * @param name the name of the data set in the container
     * @return IDataSet
     */
    IDataSet getDataSet(String name);

    /**
     * Returns the number of data sets in the container
     */
    int nDataSets();

    /**
     * Add a new data set in the container
     */
    boolean addDataSet(IDataSet dataSet);

    /**
     * Returns  a list of the loaded dataset names
     */
    List<String> dataSetNames();

    /**
     * Clear whatever the data set  may contain
     */
    void clear();

}
