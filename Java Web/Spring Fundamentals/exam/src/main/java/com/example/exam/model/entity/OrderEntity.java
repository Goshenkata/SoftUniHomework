package com.example.exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Column(nullable = false, name = "name")
    String name;

    @Column(nullable = false, name = "price")
    BigDecimal price;

    @Column(nullable = false, name = "order_time")
    LocalDateTime orderTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    CategoryEntity category;

    @Column(nullable = false, columnDefinition = "TEXT", name = "description")
    String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    UserEntity employee;

    @Override
    public String toString() {
        return "OrderEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                '}';
    }


    public String getName() {
        return name;
    }

    public OrderEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderEntity setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public OrderEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getEmployee() {
        return employee;
    }

    public OrderEntity setEmployee(UserEntity employee) {
        this.employee = employee;
        return this;
    }

    public OrderEntity() {
    }

}
