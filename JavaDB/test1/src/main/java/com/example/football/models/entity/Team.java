package com.example.football.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,unique = true)
    String name;
    @Column(nullable = false)
    Integer fanBase;
    @Column(nullable = false, columnDefinition = "TEXT")
    String stadiumName;
    @ManyToOne
    Town town;
    @OneToMany(mappedBy = "team")
    List<Player> players;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getFanBase() {
        return fanBase;
    }

    public Team setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
        return this;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public Team setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Team setTown(Town town) {
        this.town = town;
        return this;
    }
}
