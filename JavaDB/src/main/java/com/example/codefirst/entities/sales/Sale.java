package com.example.codefirst.entities.sales;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Sale extends BaseEntity {

    @ManyToOne
    Product product;
    @ManyToOne
    Customer customer;
    @ManyToOne
    StoreLocation storeLocation;
    @Column
    Date date;
}
