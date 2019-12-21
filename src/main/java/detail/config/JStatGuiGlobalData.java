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

}
