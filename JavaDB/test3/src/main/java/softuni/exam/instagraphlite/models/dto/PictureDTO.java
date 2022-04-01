package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PictureDTO implements Serializable {
    @NotNull
    String path;
    @Min(500)
    @Max(60000)
    @NotNull
    Double size;

    public PictureDTO() {
    }

    public String getPath() {
        return path;
    }

    public PictureDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public PictureDTO setSize(Double size) {
        this.size = size;
        return this;
    }
}
