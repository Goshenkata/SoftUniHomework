package com.example.exam.model.DTO.binding;

import javax.validation.constraints.Size;

public class LoginBindingModel {
    @Size(min = 5, max = 20) String username;
    @Size(min = 3) String password;

    public String getUsername() {
        return username;
    }

    public LoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginBindingModel() {
    }

}
