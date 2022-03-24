package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q4Products {
    @XmlAttribute(name = "name")
    String name;
    @XmlAttribute(name = "price")
    BigDecimal price;

    public Q4Products() {
    }

    public String getName() {
        return name;
    }

    public Q4Products setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Q4Products setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}