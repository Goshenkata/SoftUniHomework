package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameDTO implements Serializable{
    @XmlElement(name = "name")
    String name;

    public TownNameDTO() {
    }

    public String getName() {
        return name;
    }

    public TownNameDTO setName(String name) {
        this.name = name;
        return this;
    }
}
