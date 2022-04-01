package com.example.football.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, columnDefinition = "float")
    Double endurance;
    @Column(nullable = false, columnDefinition = "float")
    Double passing;
    @Column(nullable = false, columnDefinition = "float")
    Double shooting;
    @OneToMany(mappedBy = "stat")
    List<Player> players;


    public Stat() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Stat setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Stat setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getEndurance() {
        return endurance;
    }

    public Stat setEndurance(Double endurance) {
        this.endurance = endurance;
        return this;
    }

    public Double getPassing() {
        return passing;
    }

    public Stat setPassing(Double passing) {
        this.passing = passing;
        return this;
    }

    public Double getShooting() {
        return shooting;
    }

    public Stat setShooting(Double shooting) {
        this.shooting = shooting;
        return this;
    }
}
