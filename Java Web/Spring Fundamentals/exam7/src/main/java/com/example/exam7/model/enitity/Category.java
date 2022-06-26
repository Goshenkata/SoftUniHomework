package com.example.exam7.model.enitity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    CategoryName name;
    @Column(columnDefinition = "TEXT")
    String description;

    public Category(CategoryName name) {
        this.name = name;
    }
}