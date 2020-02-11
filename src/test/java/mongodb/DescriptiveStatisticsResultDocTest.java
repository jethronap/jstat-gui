package mongodb;

import detail.compute.EDAResultModel;
import mongodb.mocks.MongoDBMock;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
//import static org.springframework.data.mongodb.core.query.Criteria.query;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MongoDBMock.class)
public class DescriptiveStatisticsResultDocTest {

    @Value("${collections.compute.results}")
    String compute_results_collection;

    /**
     * Test Scenario: A DescriptiveStatisticsResult is ready to be written
     *                to the DB
     * Expected Output: Result should be properly written to the DB
     */
    @Test
    public void testInsertDescriptiveStatisticsResult(){

        MongoDBMock mongoTemplate = new MongoDBMock();

        System.out.println("Collection results: "+ compute_results_collection);
        EDAResultModel model = new EDAResultModel();
        model.name = "TestDataSet";
        model.variance = 13.0;
        model.median = 13.0;
        model.mean = 13.0;

        DescriptiveStatisticsResultDoc doc = new DescriptiveStatisticsResultDoc(model);
        mongoTemplate.save(doc, compute_results_collection);

        
        // retrieve the document and delete it
        DescriptiveStatisticsResultDoc retriveddoc = mongoTemplate.findOne(Query.query(Criteria.where("model.name").is("TestDataSet")),
                                                                            DescriptiveStatisticsResultDoc.class, compute_results_collection);

        assertNotNull(retriveddoc);
        assertEquals(retriveddoc.getName(), doc.getName() );

    }
}
