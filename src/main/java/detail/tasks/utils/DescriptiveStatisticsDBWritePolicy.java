package detail.tasks.utils;

import detail.compute.DescriptiveStatistics;
import detail.tasks.ComputeDescriptiveStatisticsTask;
import detail.tasks.TaskBase;
import mongodb.DescriptiveStatisticsResultDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class DescriptiveStatisticsDBWritePolicy implements IDBWritePolicy {



    /**
     * Take the given task and write it to the DB
     */
    @Override
    public void write(TaskBase task, MongoTemplate mongoTemplate){

        if(task == null){
            // we should log this as it looks like a miss
            System.out.println("Null task pointer");
            return;
        }

        ComputeDescriptiveStatisticsTask statsTask = (ComputeDescriptiveStatisticsTask) task;
        List<DescriptiveStatistics> results = statsTask.getResult();

        if(results.isEmpty()){
            // we should log this as this looks like a miss
            System.out.println("Empty Descriptive statistics results");
            return;
        }

        DescriptiveStatisticsResultDoc doc = new DescriptiveStatisticsResultDoc(results.get(0).getResultModel());

        if(mongoTemplate == null){
            System.out.println("MongoDB template is null");
        }

        doc.save(mongoTemplate);
        task.setId(doc.getId());
        System.out.println("Saved Task "+task.getId());
    }
}
