package com.example.football.models.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TeamDTO implements Serializable {
    @Size(min = 3)
    String name;
    @Size(min = 3)
    String stadiumName;
    @Min(1000)
    Integer fanBase;
    @Size(min = 10)
    String history;
    String townName;

    public TeamDTO() {
    }

    public String getName() {
        return name;
    }

    public TeamDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public TeamDTO setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
        return this;
    }

    public Integer getFanBase() {
        return fanBase;
    }

    public TeamDTO setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
        return this;
    }

    public String getHistory() {
        return history;
    }

    public TeamDTO setHistory(String history) {
        this.history = history;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public TeamDTO setTownName(String townName) {
        this.townName = townName;
        return this;
    }
}
