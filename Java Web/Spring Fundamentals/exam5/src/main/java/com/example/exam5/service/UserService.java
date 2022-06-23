package com.example.exam5.service;

import com.example.exam5.model.dto.LoginDTO;
import com.example.exam5.model.dto.RegistrationDTO;
import com.example.exam5.model.entity.User;
import com.example.exam5.repository.UserRepository;
import com.example.exam5.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(RegistrationDTO registrationDTO) {
        User user = mapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(user);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            currentUser.setUsername(user.getUsername());
            currentUser.setLoggedIn(true);
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return userRepository
                .findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("user is not logged in!"));
    }
}