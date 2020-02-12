package mongodb;

import detail.models.EDAResultModel;
import mongodb.mocks.MongoDBMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MongoDBMock.class)
public class DescriptiveStatisticsResultDocTest {

    @Value("${collections.compute.results}")
    String compute_results_collection;

    /**
     * Test Scenario: A DescriptiveStatisticsResult is ready to be written to the DB
     * Expected Output: Result should be properly written to the DB
     */
    @Test
    public void testInsertDescriptiveStatisticsResult(){


        MongoDBMock mongoTemplate = new MongoDBMock();

        // create dummy EDAResultModel
        EDAResultModel model = new EDAResultModel();
        model.name = "TestDataSet";
        model.variance = 13.0;
        model.median = 13.0;
        model.mean = 13.0;

        // populate a document from the model
        DescriptiveStatisticsResultDoc doc = new DescriptiveStatisticsResultDoc(model);

        // save it in the mocked db
        mongoTemplate.save(doc, compute_results_collection);

        // retrieve the document
        DescriptiveStatisticsResultDoc retriveddoc = mongoTemplate.findOne(Query.query(Criteria.where("model.name").is("TestDataSet")),
                                                                            DescriptiveStatisticsResultDoc.class, compute_results_collection);

        assertNotNull(retriveddoc);
        assertEquals(retriveddoc.getName(), doc.getName() );

    }
}
