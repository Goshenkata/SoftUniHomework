package com.example.exam4.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    Integer copies;
    @Column(nullable = false, columnDefinition = "TEXT")
    String description;
    @Enumerated(EnumType.ORDINAL)
    Genre genre;
    @Column(nullable=false)
    String imageUrl;
    @Column(nullable=false)
    String name;
    @Column(nullable=false)
    BigDecimal price;
    @Column
    String producer;
    @Column(nullable=false)
    LocalDate releaseDate;
    @ManyToOne
    User addedFrom;
    @OneToOne
    Artist artist;
}
