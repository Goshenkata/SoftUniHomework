package com.example.automapping.service;

import com.example.automapping.DTO.LoggedInUser;
import com.example.automapping.entities.User;
import com.example.automapping.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean containsEmail(String email) {
        return userRepository.findByEmailContaining(email).isPresent();
    }

    public void registerUser(String email, String password, String fullName) {
        User user = new User();
        user.setEmail(email)
                .setFullName(fullName)
                .setPassword(password)
                .setGames(new HashSet<>());
        user.setAdmin(userRepository.count() == 0);
        userRepository.save(user);
        System.out.println("registered successfully");
    }

    public LoggedInUser loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            return null;
        }
        return modelMapper.map(user.get(), LoggedInUser.class);
    }
}
