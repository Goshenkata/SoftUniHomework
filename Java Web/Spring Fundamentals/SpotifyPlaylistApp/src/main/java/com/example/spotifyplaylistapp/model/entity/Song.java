package com.example.spotifyplaylistapp.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String performer;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer duration;
    @Column
    private LocalDate releaseDate;
    @ManyToOne
    private Style style;
    @ManyToMany(mappedBy = "playlist")
    private List<User> users;
}