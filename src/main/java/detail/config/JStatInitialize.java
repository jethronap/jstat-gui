package detail.config;

import detail.datasets.MapDataContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;

/**
 * Class that handles all the initializations needed by JStat
 * in order to function properly
 */
public class JStatInitialize {


    /**
     * Initialize the data set container
     */
    public static void initializeDataContainer(){

        if(JStatGuiGlobalData.dataSetContainer != null) {
            JStatGuiGlobalData.dataSetContainer.clear();
        }

        JStatGuiGlobalData.dataSetContainer = new MapDataContainer();
    }

    /**
     * Initialize the worker pool
     */
    public static void initializeWorkerPool(){

        if(JStatGuiGlobalData.workersPool != null){
            JStatGuiGlobalData.workersPool.destroy();
        }

        JStatGuiGlobalData.workersPool = new ThreadPoolTaskExecutor();
        JStatGuiGlobalData.workersPool.setWaitForTasksToCompleteOnShutdown(false);
        JStatGuiGlobalData.workersPool.setCorePoolSize(10);

    }
}
