package com.example.football.models.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersDTO implements Serializable {
    @XmlElement(name = "player")
    List<PlayerDTO> players;

    public PlayersDTO() {
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public PlayersDTO setPlayers(List<PlayerDTO> players) {
        this.players = players;
        return this;
    }
}