package gui;

import detail.MapDataContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
        JStatGuiLoader.dataSetContainer = new MapDataContainer();

        SpringApplication.run(JStatGuiApplication.class, args);
    }

}
