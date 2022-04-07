package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentsDTO implements Serializable {
    @XmlElement(name = "apartment")
    List<ApartmentDTO> apartments;

    public ApartmentsDTO() {
    }

    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    public ApartmentsDTO setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
        return this;
    }
}