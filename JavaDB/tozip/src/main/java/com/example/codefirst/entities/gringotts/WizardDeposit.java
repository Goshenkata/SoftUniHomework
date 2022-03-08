package com.example.codefirst.entities.gringotts;

import com.example.codefirst.entities.BaseEntity;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "varchar(50)")
    public String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(60)", nullable = false)
    public String lastName;

    @Column(name = "notes", columnDefinition = "varchar(1000)", nullable = true)
    public String notes;

    @Column(name = "age", nullable = false)
    public Integer age;

    @Column(name = "magic_wand_size")
    public Short magicWandSize;

    @Column(name = "deposit_group", columnDefinition = "varchar(20)")
    public String depositGroup;

    @Column(name = "deposit_start_date")
    public LocalDateTime depositStartDate;

    @Column(name = "deposit_amount")
    public Double depositAmount;

    @Column(name = "deposit_interest")
    public Double depositInterest;

    @Column(name = "deposit_charge")
    public Double depositCharge;

    @Column(name = "deposit_expiration_date")
    public LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    public Boolean isDepositExpired;
}
