package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qd1ProductDTO implements Serializable {
    @XmlAttribute(name = "name")
    String name;
    @XmlAttribute(name = "price")
    BigDecimal price;
    @XmlAttribute(name = "seller")
    String seller;

    public Qd1ProductDTO() {
    }

    public String getName() {
        return name;
    }

    public Qd1ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Qd1ProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public Qd1ProductDTO setSeller(String seller) {
        this.seller = seller;
        return this;
    }
}
