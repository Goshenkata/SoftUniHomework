package com.example.codefirst.entities.bills;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column
    String number;
    @ManyToOne
    User owner;
}
