package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneDTO implements Serializable {
    @XmlElement(name = "register-number")
    @Size(min = 5)
    String registerNumber;
    @XmlElement(name = "capacity")
    @Positive
    Integer capacity;
    @XmlElement(name = "airline")
    @Size(min = 2)
    String airline;

    public PlaneDTO() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public PlaneDTO setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public PlaneDTO setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getAirline() {
        return airline;
    }

    public PlaneDTO setAirline(String airline) {
        this.airline = airline;
        return this;
    }
}