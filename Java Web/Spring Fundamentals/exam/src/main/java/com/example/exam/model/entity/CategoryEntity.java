package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, name = "name")
    @Enumerated(value = EnumType.STRING)
    CategoryName name;
    @Column(nullable = false, name = "needed_time")
    Integer timeNeeded;

}
