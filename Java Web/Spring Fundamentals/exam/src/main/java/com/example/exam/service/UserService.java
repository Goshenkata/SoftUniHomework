package com.example.exam.service;

import com.example.exam.model.DTO.binding.LoginBindingModel;
import com.example.exam.model.DTO.binding.UserRegistrationBindingModel;
import com.example.exam.model.entity.UserEntity;

public interface UserService {
    void register(UserRegistrationBindingModel userRegistrationBindingModel);

    UserEntity login(LoginBindingModel loginBindingModel);

    UserEntity getById(Long id);
}
