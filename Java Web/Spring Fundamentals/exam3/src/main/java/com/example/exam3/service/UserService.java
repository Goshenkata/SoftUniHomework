package com.example.exam3.service;

import com.example.exam3.model.dto.LoginDTO;
import com.example.exam3.model.dto.RegistrationDTO;
import com.example.exam3.model.entity.User;
import com.example.exam3.repository.UserReporitory;
import com.example.exam3.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserReporitory userReporitory;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean isEmailTaken(String email) {
        return userReporitory.existsByEmail(email);
    }

    public boolean isUsernameTaken(String username) {
        return userReporitory.existsByUsername(username);
    }

    public void register(RegistrationDTO registrationDTO) {
        User user = modelMapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        log.info("user " + registrationDTO.getUsername() + " registered");
        userReporitory.save(user);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> userOpt = userReporitory.findByUsername(loginDTO.getUsername());
        if (userOpt.isEmpty()) {
            log.info("user " + loginDTO.getUsername() + " does not exist");
            return false;
        } else {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                currentUser.setUsername(loginDTO.getUsername());
                currentUser.setLoggedIn(true);
                return true;
            } else {
                log.info("passwords do not match");
                return false;
            }
        }
    }

    public void logout() {
        currentUser.setUsername(null);
        currentUser.setLoggedIn(false);
    }


    public User getCurrentUser() {
        return userReporitory.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new IllegalStateException("User is not logged in!!"));
    }

    public User findByUsername(String username) {
        return userReporitory
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("error finding user with username " + username));

    }
}
