package com.example.exam2.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    BigDecimal price;
    @Column(nullable = false)
    LocalDateTime neededBefore;
    @ManyToOne
    Category category;
}
