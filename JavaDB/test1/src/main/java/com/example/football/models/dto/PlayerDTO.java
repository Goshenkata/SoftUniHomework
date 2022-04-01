package com.example.football.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDTO implements Serializable {
    @XmlElement(name = "first-name")
    @Size(min = 2)
    String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2)
    String lastName;
    @XmlElement(name = "email")
    @Email
    String email;
    @XmlElement(name = "birth-date")
    String birthDate;
    @XmlElement(name = "position")
    String position;
    @XmlElement(name = "team")
    TeamNameDTO team;
    @XmlElement(name = "town")
    TownNameDTO town;
    @XmlElement(name = "stat")
    StatNameDTO stat;



    public StatNameDTO getStat() {
        return stat;
    }

    public PlayerDTO setStat(StatNameDTO stat) {
        this.stat = stat;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PlayerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public PlayerDTO setTown(TownNameDTO town) {
        this.town = town;
        return this;
    }

    public PlayerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public PlayerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PlayerDTO setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public PlayerDTO setPosition(String position) {
        this.position = position;
        return this;
    }

    public TeamNameDTO getTeam() {
        return team;
    }

    public PlayerDTO setTeam(TeamNameDTO team) {
        this.team = team;
        return this;
    }
}
