package mongodb;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private  String MONGO_DB_URL;

    @Value("${spring.data.mongodb.port}")
    private int MONGO_DB_PORT;

    @Value("${spring.data.mongodb.database}")
    private  String MONGO_DB_NAME;


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MONGO_DB_URL);
    }

    @Bean
    @Qualifier("customMongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), MONGO_DB_NAME);
    }

}
