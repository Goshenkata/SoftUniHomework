package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TownDTO implements Serializable {
    @Size(min = 2)
    String townName;
    @Positive
    Integer population;

    public TownDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public TownDTO setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public TownDTO setPopulation(Integer population) {
        this.population = population;
        return this;
    }
}