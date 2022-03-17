package com.example.automapping.service;

import com.example.automapping.DTO.LoggedInUser;
import com.example.automapping.entities.Games;
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
            System.out.println("Incorrect username / password");
            return null;
        }
        System.out.println("Successfully logged in " + user.get().getFullName().split(" ")[0]);
        return modelMapper.map(user.get(), LoggedInUser.class);
    }

    public void getGames(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            user.getGames().stream().map(Games::getTitle).forEach(System.out::println);
        } else {
            System.out.println("Invaid logged in user");
        }
    }
}
