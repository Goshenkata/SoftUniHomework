package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q3DTORoot implements Serializable {
    @XmlElement(name = "category")
    List<Qd3DTO> categories;

    public Q3DTORoot() {
    }

    public List<Qd3DTO> getCategories() {
        return categories;
    }

    public Q3DTORoot setCategories(List<Qd3DTO> categories) {
        this.categories = categories;
        return this;
    }
}
