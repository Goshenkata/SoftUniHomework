package com.example.exam7.service;

import com.example.exam7.model.dto.LoginDto;
import com.example.exam7.model.dto.RegisterDTO;
import com.example.exam7.model.enitity.User;
import com.example.exam7.repository.UserRepository;
import com.example.exam7.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public boolean login(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsername());
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        return passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public void register(RegisterDTO registerDTO) {
        User user = mapper.map(registerDTO, User.class);
        userRepository.save(user);
    }
}