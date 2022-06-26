package com.example.exam6.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    CategoryName name;
    @Column(nullable = false)
    Integer neededTime;
    @OneToMany(mappedBy = "category")
    List<OrderEntity> orders;
}
