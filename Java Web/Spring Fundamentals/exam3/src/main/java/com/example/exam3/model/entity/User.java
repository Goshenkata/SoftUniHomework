package com.example.exam3.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String fullName;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String username;
    @OneToMany(mappedBy = "user")
    List<Ship> ships;

}