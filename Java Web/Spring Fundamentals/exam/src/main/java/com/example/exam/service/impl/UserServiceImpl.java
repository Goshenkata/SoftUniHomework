package com.example.exam.service.impl;

import com.example.exam.model.DTO.UserRegistrationBindingModel;
import com.example.exam.model.entity.UserEntity;
import com.example.exam.repository.UserRepostory;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepostory userRepostory;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepostory userRepostory, ModelMapper modelMapper) {
        this.userRepostory = userRepostory;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegistrationBindingModel userRegistrationBindingModel) {
        userRepostory.save(modelMapper.map(userRegistrationBindingModel, UserEntity.class));
    }

    @Override
    public UserEntity login(UserRegistrationBindingModel userRegistrationBindingModel) {
        UserEntity user = userRepostory.getElementByUsernameAndPassword();
        userRepostory.save(user);
        return user;
    }
}
