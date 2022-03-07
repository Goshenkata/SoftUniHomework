package com.example.codefirst.entities.bills;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class BankAccount extends BillingDetail{
    @Column
    String bankName;
    //note: Russians seething rn
    @Column
    String SWIFTcode;
}
