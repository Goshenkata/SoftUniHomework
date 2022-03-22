package com.example.jsonprocessing.DTO;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class Q2DTOProduct implements Serializable {
    @Expose
    String name;
    @Expose
    BigDecimal price;
    @Expose
    String buyerFirstName;
    @Expose
    String buyerLastName;

    public Q2DTOProduct() {
    }

    public Q2DTOProduct(String name, BigDecimal price, String buyerFirstName, String buyerLastName) {
        this.name = name;
        this.price = price;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
    }

    public String getName() {
        return name;
    }

    public Q2DTOProduct setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Q2DTOProduct setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public Q2DTOProduct setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
        return this;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public Q2DTOProduct setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
        return this;
    }
}
