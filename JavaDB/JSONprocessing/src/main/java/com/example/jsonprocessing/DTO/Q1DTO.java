package com.example.jsonprocessing.DTO;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class Q1DTO implements Serializable {
    @Expose
    String name;
    @Expose
    BigDecimal price;
    @Expose
    String sellerName;

    public Q1DTO(String name, BigDecimal price, String sellerName) {
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
    }

    public Q1DTO() {
    }

    public String getName() {
        return name;
    }

    public Q1DTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Q1DTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Q1DTO setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }
}
