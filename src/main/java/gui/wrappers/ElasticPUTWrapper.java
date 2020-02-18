package gui.wrappers;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ElasticPUTWrapper {

    @NotBlank(message="An index name is required")
    public String indexName;

    @NotBlank(message="A type name is required")
    public String typeName;

    public String docId;
}
