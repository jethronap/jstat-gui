package detail.wrappers;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnalysisFormWrapper {

    @NotBlank(message="A dataSet is required")
    public String dataSetName;

    public String colName = null;

    public String eda = null;
    public String type;
}
