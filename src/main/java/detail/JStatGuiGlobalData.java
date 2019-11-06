package detail;

import detail.tasks.LoadDatatSetTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class JStatGuiGlobalData {

    /**
     * The container used to hold the loaded data sets
     */
    public static IDataSetContainer dataSetContainer;


    /**
     * A set of worker pools used around
     */
    public static ExecutorService workersPool;


    public static LoadDatatSetTask task = null; //new LoadDatatSetTask(file, JStatGuiGlobalData.dataSetContainer);
    public static Future<JStateMessage> taskFuture = null;
}
