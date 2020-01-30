package mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DescriptiveStatisticsResultDoc
 */
@Document(collection="compute-results")
public class DescriptiveStatisticsResultDoc extends ComputeResultDocBase {


    public DescriptiveStatisticsResultDoc(){

    }


}
