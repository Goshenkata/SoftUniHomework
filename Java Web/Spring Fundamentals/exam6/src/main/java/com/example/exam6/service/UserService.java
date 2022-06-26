package com.example.exam6.service;

import com.example.exam6.model.dto.EmployeeDTO;
import com.example.exam6.model.dto.LoginDTO;
import com.example.exam6.model.dto.OrderInfoDTO;
import com.example.exam6.model.dto.RegisterDTO;
import com.example.exam6.model.entity.User;
import com.example.exam6.repository.UserRepository;
import com.example.exam6.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final ModelMapper mapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public boolean login(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByUsername(loginDTO.getUsername());
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            currentUser.setUsername(loginDTO.getUsername());
            currentUser.setLoggedIn(true);
            log.info("login successful");
            return true;
        }
        return false;
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public void register(RegisterDTO registerDTO) {
        User user = mapper.map(registerDTO, User.class);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);
    }

    public User getCurrentUser() {
        return userRepository
                .findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("user is not logged in!"));
    }

    public List<EmployeeDTO> getEmployeeInfo() {
        return userRepository.findAllEmployees();
    }
}
