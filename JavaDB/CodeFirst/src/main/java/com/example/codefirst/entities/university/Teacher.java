package com.example.codefirst.entities.university;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Teacher extends BaseEntity {
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String phoneNumber;
    @Column
    String email;
    @Column
    BigDecimal salaryPerHour;
    @OneToMany(mappedBy = "teacher")
    Set<Course> courses;
}
