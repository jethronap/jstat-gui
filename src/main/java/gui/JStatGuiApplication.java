package gui;

import detail.config.JStatGuiGlobalData;
import detail.config.JStatInitialize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * We ar running the application from an executable JAR. Thus,  it’s important to
 * have a main class that will be executed when that JAR file is run. We also need at
 * least a minimal amount of Spring configuration to bootstrap the application. 
 *
 */
@SpringBootApplication
@EnableAsync
public class JStatGuiApplication {


    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {

        return JStatGuiGlobalData.workersPool;
    }

    public static void main(String[] args) {

        // do the needed initializations
        JStatInitialize.initializeDataContainer();
        JStatInitialize.initializeWorkerPool();


        if(JStatGuiGlobalData.workersPool == null){
            throw new NullPointerException("Could not create worker pool");
        }

        // Output useful msgs
        //System.out.println("Number of threads used: " + JStatGuiGlobalData.workersPool.getActiveCount());

        SpringApplication.run(JStatGuiApplication.class, args);
    }

}
