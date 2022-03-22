package com.example.jsonprocessing.DTO;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserDTO implements Serializable {
    @Expose
    String firstName;
    @Expose
    String lastName;
    @Expose
    Integer age;

    public UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDTO setAge(Integer age) {
        this.age = age;
        return this;
    }
}
