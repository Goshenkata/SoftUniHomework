package com.example.codefirst.entities.bills;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Month;
import java.time.Year;

@Entity
public class CreditCard extends BillingDetail{
    @Column
    String cardtype;
    @Column
    Month month;
    @Column
    Year year;
}
