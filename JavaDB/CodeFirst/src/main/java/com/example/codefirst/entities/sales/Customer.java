package com.example.codefirst.entities.sales;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Customer extends BaseEntity {

    @Column
    String name;
    @Column
    String email;
    @Column
    String creditCardNumber;
    @OneToMany(mappedBy = "customer")
    Set<Sale> sales;
}
