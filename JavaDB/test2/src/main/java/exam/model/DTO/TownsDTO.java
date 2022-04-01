package exam.model.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsDTO implements Serializable {
    @XmlElement(name = "town")
    List<TownDTO> towns;

    public TownsDTO() {
    }

    public List<TownDTO> getTowns() {
        return towns;
    }

    public TownsDTO setTowns(List<TownDTO> towns) {
        this.towns = towns;
        return this;
    }
}