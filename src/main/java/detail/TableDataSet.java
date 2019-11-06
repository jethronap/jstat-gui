package detail;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;

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

        File file = new File(fileName.getAbsolutePath());
        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("-").build();

        try{
            this.dataSet = Table.read().usingOptions(options);
        }
        catch (IOException e){
            System.out.println("An excpetion was thrown: "+e.toString());
            //throw new IOException(e.getCause());
        }


    }


    private Table dataSet;
}
