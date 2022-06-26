package com.example.exam6.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    BigDecimal price;
    @Column(nullable = false)
    LocalDateTime localDateTime;
    @ManyToOne
    Category category;
    @Column(nullable = false, columnDefinition = "TEXT")
    String description;
    @ManyToOne
    User employee;
}