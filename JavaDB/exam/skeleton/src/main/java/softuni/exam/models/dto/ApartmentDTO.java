package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDTO implements Serializable {
    @XmlElement(name = "apartmentType")
    String apartmentType;
    @XmlElement(name = "area")
    @Min(40)
    Double area;
    @XmlElement(name = "town")
    String town;

    public ApartmentDTO() {
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public ApartmentDTO setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public ApartmentDTO setArea(Double area) {
        this.area = area;
        return this;
    }

    public String getTown() {
        return town;
    }

    public ApartmentDTO setTown(String town) {
        this.town = town;
        return this;
    }
}