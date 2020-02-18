package gui.wrappers;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnalysisFormWrapper {

    public String eda = null;
    public String linear_regression = null;
    public String non_linear_regression = null;
    public String logistic_regression = null;
    public String kmeans_clustering = null;

}
