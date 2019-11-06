package detail;

import java.util.concurrent.ExecutorService;

public class JStatGuiGlobalData {

    /**
     * The container used to hold the loaded data sets
     */
    public static IDataSetContainer dataSetContainer;


    /**
     * A set of worker pools used around
     */
    public static ExecutorService workersPool;
}
