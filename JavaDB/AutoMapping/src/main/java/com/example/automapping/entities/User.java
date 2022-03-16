package com.example.automapping.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String fullName;
    @ManyToMany
            @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    Set<Games> games;
    @Column(nullable = false)
    boolean isAdmin;
    @OneToMany(mappedBy = "buyer")
    Set<Order> orders;
}
