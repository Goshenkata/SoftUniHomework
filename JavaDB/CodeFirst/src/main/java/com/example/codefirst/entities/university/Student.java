package com.example.codefirst.entities.university;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Student extends BaseEntity {
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String phoneNumber;
    @Column
    Double averageGrade;
    @Column
    Integer attendence;
    @ManyToMany(mappedBy = "students")
    Set<Course> courses;

}
