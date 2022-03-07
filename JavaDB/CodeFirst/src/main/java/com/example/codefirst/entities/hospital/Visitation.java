package com.example.codefirst.entities.hospital;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Visitation extends BaseEntity {
    @Column
    private LocalDate date;
    @ElementCollection
    private Set<String> comments;
    @ManyToOne
    private Patient patient;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<String> getComments() {
        return comments;
    }

    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
