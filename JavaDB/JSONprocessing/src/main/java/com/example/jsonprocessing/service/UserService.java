package com.example.jsonprocessing.service;

import com.example.jsonprocessing.DTO.UserDTO;
import com.example.jsonprocessing.entities.User;
import com.example.jsonprocessing.repository.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String USERS_JSON_PATH = "src/main/resources/09. DB-Advanced-JSON-Processing-Exercises/users.json";
    UserRepository userRepository;
    Gson gson;
    ModelMapper mapper;

    public UserService(UserRepository userRepository, Gson gson, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    public boolean isEmpty() {
        return userRepository.count() == 0;
    }

    public void seedUser() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USERS_JSON_PATH);
        UserDTO[] userDTOs = gson.fromJson(fileReader, UserDTO[].class);
        List<User> users = Arrays.stream(userDTOs)
                .map(u -> mapper.map(u, User.class))
                .toList();
        userRepository.saveAll(users);
    }

    public User randomUser() {
        Integer min = userRepository.getMinID();
        Integer max = userRepository.getMaxID();
        Random random = new Random();
        return userRepository.getById(random.nextLong(max) + min);
    }
}
