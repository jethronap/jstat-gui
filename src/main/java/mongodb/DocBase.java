package mongodb;


import org.springframework.data.annotation.Id;

/**
 * Base class for all MongoDB documents
 */
public class DocBase {


    /**
     * Return the id of the document
     */
    public String getId() {
        return Id;
    }

    /**
     * Set the id of the document
     */
    public void setId(String id) {
        Id = id;
    }

    /**
     * Id of the document
     */
    @Id
    private String Id;


}
