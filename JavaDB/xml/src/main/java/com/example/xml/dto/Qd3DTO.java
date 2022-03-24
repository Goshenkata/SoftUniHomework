package com.example.xml.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qd3DTO implements Serializable {
    @XmlAttribute(name = "name")
    String name;
    @XmlElement(name = "products-count")
    Integer productsCount;
    @XmlElement(name = "average-price")
    Double averagePrice;
    @XmlElement(name = "total-revenue")
    Double totalRevenue;

    public Qd3DTO() {
    }


    public String getName() {
        return name;
    }

    public Qd3DTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public Qd3DTO setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
        return this;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Qd3DTO setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Qd3DTO setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }
}
