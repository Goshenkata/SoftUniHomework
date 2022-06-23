package com.example.exam3.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    LocalDate created;
    @Column(nullable = false)
    Long health;
    @Column(nullable = false)
    Long power;
    @ManyToOne
    User user;
    @ManyToOne
    Category category;
}