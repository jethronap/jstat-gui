package detail;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public class LoadDatatSetTask implements Callable<JStateMessage> {

    public LoadDatatSetTask(String fileName, IDataSetContainer container){

        this.file = new File(fileName);
        this.container = container;
    }

    public LoadDatatSetTask(File file, IDataSetContainer container){
        this.file = file;
        this.container = container;
    }


    @Override
    public JStateMessage call()throws Exception{

        // reade the dataSet
        dataSet = new TableDataSet();
        dataSet.loadFrom(file);

        // we loaded the dataSet
        container.addDataSet(dataSet);

        // no notify the Messaging that
        msg = new JStateMessage("Data set "+dataSet.getName()+" is loaded");

        return msg;
    }


    /**
     * The file from which to read the data set
     */
    private File file;

    /**
     * Instance that holds the loaded data set
     */
    private IDataSet dataSet;

    /**
     * The global container into which the result is stored
     */
    private IDataSetContainer container;

    /**
     * Message to the client code
     */
    private JStateMessage msg;


}
