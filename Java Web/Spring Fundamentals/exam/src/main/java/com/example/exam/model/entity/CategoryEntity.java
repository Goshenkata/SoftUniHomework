package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.CategoryName;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false, name = "name")
    @Enumerated(value = EnumType.STRING)
    CategoryName name;
    @Column(nullable = false, name = "needed_time")
    Integer timeNeeded;

    public CategoryEntity() {
    }

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
    public CategoryEntity setTimeNeeded() {
        switch (name) {
            case COFFEE:
                timeNeeded = 2;
                break;
            case CAKE:
                timeNeeded = 10;
                break;
            case DRINK:
                timeNeeded = 1;
                break;
            case OTHER:
                timeNeeded = 5;
                break;
        }
        return this;
    }

}
