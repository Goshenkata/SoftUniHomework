package com.example.codefirst.entities.sales;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
public class StoreLocation extends BaseEntity {
    @Column
    String locationName;
    @OneToMany(mappedBy = "storeLocation")
    Set<Sale> sales;
}
