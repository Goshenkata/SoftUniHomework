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

    public CategoryName getName() {
        return name;
    }

    public CategoryEntity setName(CategoryName name) {
        this.name = name;
        return this;
    }

    public Integer getTimeNeeded() {
        return timeNeeded;
    }

    public CategoryEntity setTimeNeeded(Integer timeNeeded) {
        this.timeNeeded = timeNeeded;
        return this;
    }

    public CategoryEntity() {
    }
}
