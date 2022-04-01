package com.example.football.models.dto;


import com.example.football.models.entity.Position;

public class BestPlayerDTO {
    String firstName;
    String lastName;
    Position position;
    String teamName;
    String stadium;

    @Override
    public String toString() {
        return String.format("Player - %s %s%n" +
                "\tPosition - %s%n" +
                "\tTeam - %s%n" +
                "\tStadium - %s", firstName, lastName, position.name(), teamName, stadium);
    }

    public BestPlayerDTO(String firstName, String lastName, Position position, String teamName, String stadium) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.teamName = teamName;
        this.stadium = stadium;
    }

    public BestPlayerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public BestPlayerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BestPlayerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public BestPlayerDTO setPosition(Position position) {
        this.position = position;
        return this;
    }

    public String getStadium() {
        return stadium;
    }

    public BestPlayerDTO setStadium(String stadium) {
        this.stadium = stadium;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public BestPlayerDTO setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }
}