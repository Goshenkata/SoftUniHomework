package com.example.football.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,unique = true)
    String name;
    @Column(nullable = false)
    Integer population;
    @Column(nullable = false, columnDefinition = "TEXT")
    String travelGuide;
    @OneToMany(mappedBy = "town")
    List<Team> teams;
    @OneToMany(mappedBy = "town")
    List<Player> players;
    public Town() {
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Town setTeams(List<Team> teams) {
        this.teams = teams;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Town setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Town setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public Town setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public Town setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}
