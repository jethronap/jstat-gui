package gui.wrappers;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ElasticPUTWrapper {

    @NotBlank(message="An index name is required")
    public String indexName;

    @NotBlank(message="A type name is required")
    public String typeName;

    public String docId;

    @NotBlank(message="A json formated string is required")
    public String jsonInputText;


}
