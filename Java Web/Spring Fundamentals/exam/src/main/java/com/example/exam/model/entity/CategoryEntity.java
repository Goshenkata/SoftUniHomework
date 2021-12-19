package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.CategoryName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    CategoryName name;
    @Column(nullable = false)
    Integer time;

}
