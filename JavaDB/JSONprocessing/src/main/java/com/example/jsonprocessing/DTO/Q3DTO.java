package com.example.jsonprocessing.DTO;

import com.example.jsonprocessing.entities.Category;
import com.google.gson.annotations.Expose;

import java.awt.*;
import java.io.Serializable;

public class Q3DTO implements Serializable {
    @Expose
    String category;
    @Expose
    Integer productsCount;
    @Expose
    Double averagePrice;
    @Expose
    Double totalRevenue;
    Category categoryEnt;

    public Q3DTO(String category, Integer productsCount, Category categoryEnt) {
        this.category = category;
        this.productsCount = productsCount;
    }

    public Category getCategoryEnt() {
        return categoryEnt;
    }

    public Q3DTO setCategoryEnt(Category categoryEnt) {
        this.categoryEnt = categoryEnt;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Q3DTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public Q3DTO setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
        return this;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Q3DTO setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Q3DTO setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }
}
