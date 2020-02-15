package detail.config;

import detail.datasets.IDataSetContainer;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



public class JStatGuiGlobalData {

    /**
     * The container used to hold the loaded data sets
     */
    public static IDataSetContainer dataSetContainer;

    /**
     * A set of worker pools used around
     */
    public static ThreadPoolTaskExecutor workersPool;

    /**
     * Collection name that control results are stored
     */
    public static  String control_tasks_collection = "control_tasks_coll";


    /**
     * Collection name that holds the computed result
     */
    public static String compute_results_collection = "compute_results_coll";




}
