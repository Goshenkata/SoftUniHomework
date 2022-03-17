package com.example.automapping.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;
    @Column
    String trailer;
    @Column(nullable = false)
    Long size;
    @Column
    BigDecimal price;
    @Column
    String description;
    @Column
    LocalDate releaseDate;
    @Column
    String imageUrl;
    @ManyToMany(mappedBy = "games")
    Set<User> users;

    public Long getId() {
        return id;
    }

    public Games setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Games setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTrailer() {
        return trailer;
    }

    public Games setTrailer(String trailer) {
        this.trailer = trailer;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public Games setSize(Long size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Games setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Games setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Games setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Games setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Games setUsers(Set<User> users) {
        this.users = users;
        return this;
    }
}
