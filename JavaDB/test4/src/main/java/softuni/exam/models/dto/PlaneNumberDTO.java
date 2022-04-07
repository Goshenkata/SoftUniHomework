package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneNumberDTO implements Serializable {
    @XmlElement(name = "register-number")
    String registerNumber;

    public PlaneNumberDTO() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public PlaneNumberDTO setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }
}