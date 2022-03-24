package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q2ProductsDTO implements Serializable {
    @XmlElement(name = "name")
    String name;
    @XmlElement(name = "price")
    BigDecimal price;
    @XmlElement(name = "buyer-first-name")
    String buyerFirstName;
    @XmlElement(name = "buyer-last-name")
    String buyerLastName;

    public Q2ProductsDTO() {
    }

    public String getName() {
        return name;
    }

    public Q2ProductsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Q2ProductsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public Q2ProductsDTO setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
        return this;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public Q2ProductsDTO setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
        return this;
    }
}
