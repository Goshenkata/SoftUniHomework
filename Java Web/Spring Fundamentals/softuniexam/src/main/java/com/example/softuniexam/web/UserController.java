package com.example.softuniexam.web;

import com.example.softuniexam.model.dto.LoginDTO;
import com.example.softuniexam.model.dto.RegisterDTO;
import com.example.softuniexam.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final CurrentUser currentUser;

    @GetMapping("/login")
    public String login() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser.clear();
        return "redirect:/";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }
}
