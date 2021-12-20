package com.example.exam.service.impl;

import com.example.exam.model.DTO.binding.LoginBindingModel;
import com.example.exam.model.DTO.binding.UserRegistrationBindingModel;
import com.example.exam.model.entity.UserEntity;
import com.example.exam.repository.UserRepostory;
import com.example.exam.service.UserService;
import com.example.exam.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepostory userRepostory;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepostory userRepostory, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepostory = userRepostory;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserRegistrationBindingModel userRegistrationBindingModel) {
        userRepostory.save(modelMapper.map(userRegistrationBindingModel, UserEntity.class));
    }

    @Override
    public UserEntity login(LoginBindingModel loginBindingModel) {
        UserEntity user = userRepostory.getByUsernameAndPassword(loginBindingModel.getUsername(), loginBindingModel.getPassword());
        userRepostory.save(user);
        return user;
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepostory.findById(id).orElse(null);
    }

}
