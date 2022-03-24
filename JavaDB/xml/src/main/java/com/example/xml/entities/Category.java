package com.example.xml.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @ManyToMany(mappedBy = "categories")
    Set<Product> products;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }
}
