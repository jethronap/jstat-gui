package mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Abstract class that represents a document that modesl
 * a computation result
 */
public abstract class ComputeResultDocBase extends DocBase {

    /**
     * Useful enumeration to distinguish between the
     * result documents
     */
    public enum Type{DESCRIPTIVE_STATS, LINEAR_REGRESSION};

    /**
     * Save the document in the provided MongoDB instance
     */
    public abstract void save(MongoTemplate db);

    /**
     * Returns the collection name the document belongs to
     */
    public abstract String getCollectionName();
}
