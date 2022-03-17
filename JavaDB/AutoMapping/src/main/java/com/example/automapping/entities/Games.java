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
}
