package com.example.exam.service;

import com.example.exam.model.DTO.LoginBindingModel;
import com.example.exam.model.DTO.UserRegistrationBindingModel;
import com.example.exam.model.entity.UserEntity;

public interface UserService {
    void register(UserRegistrationBindingModel userRegistrationBindingModel);

    UserEntity login(LoginBindingModel loginBindingModel);
}
