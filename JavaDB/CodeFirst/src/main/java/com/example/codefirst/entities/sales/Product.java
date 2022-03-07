package com.example.codefirst.entities.sales;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Product extends BaseEntity {
    @Column
    String name;
    @Column
    Double quantity;
    @Column
    BigDecimal price;
    @OneToMany(mappedBy = "product")
    Set<Sale> sales;
}
