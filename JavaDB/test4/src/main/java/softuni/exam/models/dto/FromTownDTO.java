package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "from-town")
public class FromTownDTO implements Serializable {
//    @XmlElement(name = "name")
    String name;

    public FromTownDTO() {
    }

    public String getName() {
        return name;
    }

    public FromTownDTO setName(String name) {
        this.name = name;
        return this;
    }
}