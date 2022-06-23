package com.example.exam3.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.ORDINAL)
    CategoryEnum name;
    @Column(columnDefinition = "TEXT")
    String description;
    @OneToMany(mappedBy = "category")
    List<Ship> ships;

    public Category(CategoryEnum name) {
        this.name = name;
    }
}