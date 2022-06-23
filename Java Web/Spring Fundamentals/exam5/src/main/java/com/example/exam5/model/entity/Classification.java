package com.example.exam5.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    ClassificationName classificationName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "classification")
    private List<Task> tasks;
}