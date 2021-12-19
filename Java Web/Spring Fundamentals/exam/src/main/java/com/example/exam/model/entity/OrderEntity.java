package com.example.exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @Column(nullable = false, name = "name")
    String name;
    @Column(nullable = false, name = "price")
    BigDecimal price;
    @Column(nullable = false, name = "order_time")
    LocalDateTime orderTime;
    @ManyToOne
    CategoryEntity category;
    @Column(nullable = false, columnDefinition = "TEXT", name = "description")
    String description;
    @ManyToOne
    UserEntity employee;
}
