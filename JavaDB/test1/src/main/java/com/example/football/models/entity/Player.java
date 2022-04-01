package com.example.football.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    LocalDate birthDate;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Enumerated(EnumType.ORDINAL)
    Position position;
    @ManyToOne
    Stat stat;
    @ManyToOne
    Team team;
    @ManyToOne
    Town town;

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public Player setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Player setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Player setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Player setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Stat getStat() {
        return stat;
    }

    public Player setStat(Stat stat) {
        this.stat = stat;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public Player setTeam(Team team) {
        this.team = team;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Player setTown(Town town) {
        this.town = town;
        return this;
    }
}
