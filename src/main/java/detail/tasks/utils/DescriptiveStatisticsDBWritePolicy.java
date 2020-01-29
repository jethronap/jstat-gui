package detail.tasks.utils;

import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.TaskBase;
import org.springframework.beans.factory.annotation.Value;

public class DescriptiveStatisticsDBWritePolicy implements IDBWritePolicy {

    @Value("${spring.data.mongodb.host}")
    public static String MONGO_DB_DESCRIPTIVE_STATS_RESULT_COLLECTION;

    /**
     * Take the given task and write it to the DB
     */
    @Override
    public void write(TaskBase task){

        ComputeDescriptiveStatisticsTask statsTask = (ComputeDescriptiveStatisticsTask) task;

        // we need to create a MongoDB document

    }


}
