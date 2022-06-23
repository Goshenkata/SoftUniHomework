package com.example.exam4.service;

import com.example.exam4.currentUser.CurrentUser;
import com.example.exam4.model.dto.LoginDTO;
import com.example.exam4.model.dto.RegistrationDTO;
import com.example.exam4.model.entity.Artist;
import com.example.exam4.model.entity.User;
import com.example.exam4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public void register(RegistrationDTO registrationDTO) {
        User user = mapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(user);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByUsername(loginDTO.getUsername());
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    public User getCurrentUser() {
        return userRepository
                .findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new IllegalStateException("No logged in user!"));
    }
}
