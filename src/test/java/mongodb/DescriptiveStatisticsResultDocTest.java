package mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MongoConfig.class)
//@EnableMongoRepositories//(basePackageClasses=ComputeResultRepository.class)

//@ContextConfiguration//(classes=ComputeResultRepository.class)
public class DescriptiveStatisticsResultDocTest {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Test Scenario: A DescriptiveStatisticsResult is ready to be written
     *                to the DB
     * Expected Output: Result should be properly written to the DB
     */
    @Test
    public void testInsertDescriptiveStatisticsResult(){

        DescriptiveStatisticsResultDoc doc = new DescriptiveStatisticsResultDoc();
        mongoTemplate.save(doc, "ComputeResults");
        //repository.save(doc);

    }
}
