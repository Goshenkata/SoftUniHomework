package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamNameDTO implements Serializable{
    @XmlElement(name = "name")
    String name;

    public TeamNameDTO() {
    }

    public String getName() {
        return name;
    }

    public TeamNameDTO setName(String name) {
        this.name = name;
        return this;
    }
}