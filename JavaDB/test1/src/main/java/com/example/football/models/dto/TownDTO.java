package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TownDTO implements Serializable {
    @Size(min = 2)
    String name;
    @Positive
    Integer population;
    @Size(min = 2)
    String travelGuide;


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
