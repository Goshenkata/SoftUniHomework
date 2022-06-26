package com.example.spotifyplaylistapp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    StyleName name;
    @Column(columnDefinition = "TEXT")
    String description;

    public Style(StyleName name) {
        this.name = name;
    }
}