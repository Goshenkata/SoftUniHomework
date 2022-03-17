package com.example.automapping.DTO;


import org.springframework.stereotype.Component;

@Component
public class LoggedInUser {

    Long id;
    String email;
    String password;
    String fullName;

    public LoggedInUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedInUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoggedInUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoggedInUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LoggedInUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
