package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassangerEmailDTO implements Serializable {
    @XmlElement(name = "email")
    String email;

    public PassangerEmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public PassangerEmailDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}