package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsDTO implements Serializable {
    @XmlElement(name = "stat")
    List<StatDTO> stats;

    public StatsDTO() {
    }

    public List<StatDTO> getStats() {
        return stats;
    }

    public StatsDTO setStats(List<StatDTO> stats) {
        this.stats = stats;
        return this;
    }
}
