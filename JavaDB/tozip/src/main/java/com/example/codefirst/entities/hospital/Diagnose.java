package com.example.codefirst.entities.hospital;

import com.example.codefirst.entities.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Diagnose extends BaseEntity {
    @Column
    private String name;
    @ElementCollection
    private List<String> comments;
    @ManyToOne
    private Patient patient;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
