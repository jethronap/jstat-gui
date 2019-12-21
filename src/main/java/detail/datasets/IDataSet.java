package detail.datasets;

import java.io.File;

/**
 * General interface to model a data set
 */
public interface IDataSet<ItemTp> {

    /**
     * Returns the name of the data set
     */
    String getName();

    /**
     * Returns the characterizing size of the data set.
     * The characterizing size is typically the size
     * that allows us to loop over the data set
     */
    int size();


    /**
     * Returns the i-th data
     */
    ItemTp getItem(int i);


    /**
     * Load the data set from the given filename
     */
    void loadFrom(File fileName);
}
