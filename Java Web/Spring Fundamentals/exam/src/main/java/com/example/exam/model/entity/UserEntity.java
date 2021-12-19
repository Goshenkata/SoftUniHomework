package com.example.exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserEntity extends BaseEntity{
    @Column(unique = true, nullable = false)
    String username;
    @Column
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String password;
    @Column(unique = true, nullable = false)
    String email;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
