package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentNameDTO implements Serializable {
    @XmlElement(name = "name")
    String firstName;

    public AgentNameDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AgentNameDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}