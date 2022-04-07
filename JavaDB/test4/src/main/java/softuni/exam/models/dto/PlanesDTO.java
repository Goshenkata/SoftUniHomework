package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanesDTO implements Serializable {
    @XmlElement(name = "plane")
    List<PlaneDTO> planeDTOList;

    public PlanesDTO() {
    }

    public List<PlaneDTO> getPlaneDTOList() {
        return planeDTOList;
    }

    public PlanesDTO setPlaneDTOList(List<PlaneDTO> planeDTOList) {
        this.planeDTOList = planeDTOList;
        return this;
    }
}