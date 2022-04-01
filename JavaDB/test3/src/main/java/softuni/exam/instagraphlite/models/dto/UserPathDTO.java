package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserPathDTO {
    @XmlElement(name = "path")
    String path;

    public UserPathDTO() {
    }

    public String getPath() {
        return path;
    }

    public UserPathDTO setPath(String path) {
        this.path = path;
        return this;
    }
}