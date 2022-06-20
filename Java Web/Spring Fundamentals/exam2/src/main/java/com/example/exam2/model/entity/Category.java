package com.example.exam2.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}