package gui;

import detail.JStatGuiGlobalData;
import detail.MapDataContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;


/**
 * We ar running the application from an executable JAR. Thus,  it’s important to
 * have a main class that will be executed when that JAR file is run. We also need at
 * least a minimal amount of Spring configuration to bootstrap the application. 
 *
 */
@SpringBootApplication
public class JStatGuiApplication {

    public static void main(String[] args) {

        // do the needed initializations

        // global object holding the loaded data sets
        JStatGuiGlobalData.dataSetContainer = new MapDataContainer();

        // the worker pool
        JStatGuiGlobalData.workersPool = Executors.newFixedThreadPool(5);


        if(JStatGuiGlobalData.workersPool == null){
            throw new NullPointerException("Could not create worker pool");
        }

        SpringApplication.run(JStatGuiApplication.class, args);

        //System.out.println("Terminating...");

        // once the app stops shutdown the threads
        //JStatGuiGlobalData.workersPool.shutdown();
    }

}
