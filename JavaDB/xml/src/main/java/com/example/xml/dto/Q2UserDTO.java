package com.example.xml.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q2UserDTO implements Serializable {
    @XmlAttribute(name = "first-name")
    String firstName;
    @XmlAttribute(name = "last-name")
    String lastName;
    @XmlElement(name = "sold-products")
    List<Q2ProductsDTO> products;

    public Q2UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Q2UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Q2UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Q2ProductsDTO> getProducts() {
        return products;
    }

    public Q2UserDTO setProducts(List<Q2ProductsDTO> products) {
        this.products = products;
        return this;
    }
}
