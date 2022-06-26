package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.LoginDTO;
import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class UserService {
    @RequestBody
    private final CurrentUser currentUser;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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

    /**
     * @param loginDTO login data
     * @return true if login is successful and false is unsuccessful
     */
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
}
