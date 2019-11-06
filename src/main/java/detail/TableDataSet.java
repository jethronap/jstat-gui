package detail;

import tech.tablesaw.api.Table;

import java.io.File;

/**
 * Wrapper to the Tablesaw Table class
 */
public class TableDataSet implements IDataSet {

    /**
     * Returns the name of the data set
     */
    public String getName(){
        return dataSet.name();
    }


    /**
     * Load the data set from the given filename
     */
    public void loadFrom(File fileName){

    }


    private Table dataSet;
}
