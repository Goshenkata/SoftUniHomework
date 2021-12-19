package com.example.exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Order extends BaseEntity{
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    BigDecimal price;
    @Column(nullable = false)
    LocalDateTime orderTime;
    @ManyToOne
    CategoryEntity category;
    @Column(nullable = false, columnDefinition = "TEXT")
    String description;
    @ManyToOne
    UserEntity employee;
}
