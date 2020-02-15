package detail.wrappers;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class EDAAnalysisFormWrapper {

    @NotBlank(message="A dataSet is required")
    public String dataSetName;

    @NotBlank(message="A column is required")
    public String colName = null;

}
