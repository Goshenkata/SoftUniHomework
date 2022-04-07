package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentIdDTO implements Serializable {
    @XmlElement(name = "id")
    Long id;

    public ApartmentIdDTO() {
    }

    public Long getId() {
        return id;
    }

    public ApartmentIdDTO setId(Long id) {
        this.id = id;
        return this;
    }
}