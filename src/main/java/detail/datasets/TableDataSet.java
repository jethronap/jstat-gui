package detail.datasets;

import detail.datasets.IDataSet;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Wrapper to the Tablesaw Table class
 */
public class TableDataSet implements IDataSet<Column> {

    /**
     * Returns the name of the data set
     */
    public String getName(){
        return dataSet.name();
    }

    /**
     * Load the data set from the given filename
     */
    @Override
    public void loadFrom(File fileName){

        File file = new File(fileName.getAbsolutePath());
        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("-").build();

        try{
            this.dataSet = Table.read().usingOptions(options);
        }
        catch (IOException e){
            System.out.println("An exception was thrown: "+e.toString());
            //throw new IOException(e.getCause());
        }
    }

    /**
     * Returns the characterizing size of the data set.
     * The characterizing size is typically the size
     * that allows us to loop over the data set
     */
    @Override
    public int size(){
        return this.dataSet.columnCount();
    }

    /**
     * Returns the i-th data
     */
    @Override
    public Column getItem(int i){
        return this.dataSet.column(i);
    }


    /**
     * Returns the data item associated with the given name
     */
    @Override
    public Column getItem(String name){
        return this.dataSet.column(name);
    }


    /**
     * Returns a list with the column names
     */
    @Override
    public List<String> getColumnNames(){

        return this.dataSet.columnNames();
    }

    private Table dataSet;
}
