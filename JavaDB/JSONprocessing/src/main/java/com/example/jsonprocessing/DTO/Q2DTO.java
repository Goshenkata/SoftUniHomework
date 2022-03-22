package com.example.jsonprocessing.DTO;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Q2DTO implements Serializable {
    @Expose
    String firstName;
    @Expose
    String lastName;
    @Expose
    List<Q2DTOProduct> soldProducts;

    public Q2DTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Q2DTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Q2DTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Q2DTOProduct> getSoldProducts() {
        return soldProducts;
    }

    public Q2DTO setSoldProducts(List<Q2DTOProduct> soldProducts) {
        this.soldProducts = soldProducts;
        return this;
    }
}
