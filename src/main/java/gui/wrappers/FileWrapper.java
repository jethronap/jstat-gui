package gui.wrappers;

import lombok.Data;
import javax.validation.constraints.NotBlank;


/**
 * Wrapper to File so that we can perform validation
 */
@Data
public class FileWrapper {

    @NotBlank(message="Name is required")
    public String fileName;

}
