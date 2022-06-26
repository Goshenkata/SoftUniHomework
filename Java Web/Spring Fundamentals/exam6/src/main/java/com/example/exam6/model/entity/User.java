package com.example.exam6.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    @Column
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    @OneToMany(mappedBy = "employee")
    List<OrderEntity> orders;
}