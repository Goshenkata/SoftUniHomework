package com.example.xml.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column
    BigDecimal price;
    @ManyToOne
    User buyer;
    @ManyToOne
    User seller;
    @ManyToMany
    @JoinTable(name = "products_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories;

    public Product() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Product setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public Product setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
}
