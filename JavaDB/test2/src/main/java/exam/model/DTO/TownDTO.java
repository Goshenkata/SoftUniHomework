package exam.model.DTO;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownDTO implements Serializable {
    @Size(min = 2)
    @XmlElement(name = "name")
    String name;
    @Positive
    @XmlElement(name = "population")
    Integer population;
    @Size(min = 10)
    @XmlElement(name = "travel-guide")
    String travelGuide;

    public TownDTO() {
    }

    public String getName() {
        return name;
    }

    public TownDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public TownDTO setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public TownDTO setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}
