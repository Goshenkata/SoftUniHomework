package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatDTO implements Serializable {
    @XmlElement(name = "passing")
    @Positive
    Double passing;
    @XmlElement(name = "shooting")
    @Positive
    Double shooting;
    @XmlElement(name = "endurance")
    @Positive
    Double endurance;

    public StatDTO() {
    }

    public Double getPassing() {
        return passing;
    }

    public StatDTO setPassing(Double passing) {
        this.passing = passing;
        return this;
    }

    public Double getShooting() {
        return shooting;
    }

    public StatDTO setShooting(Double shooting) {
        this.shooting = shooting;
        return this;
    }

    public Double getEndurance() {
        return endurance;
    }

    public StatDTO setEndurance(Double endurance) {
        this.endurance = endurance;
        return this;
    }
}