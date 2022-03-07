package com.example.codefirst.entities.university;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class Course extends BaseEntity {
    @Column
    String firstName;
    @Column
    String desciption;
    @Column
    LocalDateTime startDate;
    @Column
    LocalDateTime endDate;
    @ManyToMany
            @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> students;
    @ManyToOne
    Teacher teacher;
}
