package detail;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.File;

/**
 * Wrapper to File so that we can perform validation
 */
@Data
public class FileWrapper {


    @NotBlank(message="Name is required")
    public String fileName;

    //File file;
}
