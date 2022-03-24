package com.example.xml.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    @Column
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column
    Integer age;
    @ManyToMany
    Set<User> friends;
    @OneToMany(mappedBy = "seller")
    Set<Product> productsSold;
    @OneToMany(mappedBy = "buyer")
    Set<Product> productsBrought;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public User setFriends(Set<User> friends) {
        this.friends = friends;
        return this;
    }

    public Set<Product> getProductsSold() {
        return productsSold;
    }

    public User setProductsSold(Set<Product> productsSold) {
        this.productsSold = productsSold;
        return this;
    }

    public Set<Product> getProductsBrought() {
        return productsBrought;
    }

    public User setProductsBrought(Set<Product> productsBrought) {
        this.productsBrought = productsBrought;
        return this;
    }
}
