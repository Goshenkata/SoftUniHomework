package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatNameDTO implements Serializable {
    @XmlElement(name = "id")
    Long id;

    public StatNameDTO() {
    }

    public Long getId() {
        return id;
    }

    public StatNameDTO setId(Long name) {
        this.id = name;
        return this;
    }
}