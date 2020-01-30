package mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputeResultRepository extends MongoRepository<ComputeResultDocBase, String> {


}
