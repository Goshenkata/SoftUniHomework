package com.example.xml.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q4User implements Serializable {
    @XmlAttribute(name = "first-name")
    String firstName;
    @XmlAttribute(name = "last-name")
    String lastName;
    @XmlAttribute(name = "age")
    Integer age;
    @XmlElement(name = "sold-products")
    List<Q4SoldProducts> soldProducts;

    public Q4User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Q4User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Q4User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Q4User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public List<Q4SoldProducts> getSoldProducts() {
        return soldProducts;
    }

    public Q4User setSoldProducts(List<Q4SoldProducts> soldProducts) {
        this.soldProducts = soldProducts;
        return this;
    }
}
