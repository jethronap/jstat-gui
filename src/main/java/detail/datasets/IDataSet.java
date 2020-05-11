package detail.datasets;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
     * Returns the data item associated with the given name
     */
    ItemTp getItem(String name);


    /**
     * Load the data set from the given filename
     */
    void loadFrom(File fileName) throws IOException;


    /**
     * Returns a list with the column names
     */
    List<String> getColumnNames();
}
