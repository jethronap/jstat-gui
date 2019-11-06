package detail;

import java.io.File;

/**
 * General interface to model a data set
 */
public interface IDataSet {

    /**
     * Returns the name of the data set
     */
    String getName();


    /**
     * Load the data set from the given filename
     */
    void loadFrom(File fileName);
}
