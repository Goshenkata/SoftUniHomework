package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "to-town")
public class ToTownDTO implements Serializable {
//    @XmlElement(name = "name")
    String name;

    public ToTownDTO() {
    }

    public String getName() {
        return name;
    }

    public ToTownDTO setName(String name) {
        this.name = name;
        return this;
    }
}