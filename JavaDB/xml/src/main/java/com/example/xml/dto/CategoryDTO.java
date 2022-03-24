package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDTO implements Serializable {
    @XmlElement(name = "name")
    String name;

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }


    public CategoryDTO setName(String name) {
        this.name = name;
        return this;
    }
}
