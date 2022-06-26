package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.LoginDTO;
import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
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

    @ModelAttribute("wrongCredentials")
    public boolean wrongCredentials(){
        return false;
    }


    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        boolean passwordNotMatch = !registerDTO.getPassword().equals(registerDTO.getConfirmPassword());
        boolean emailIsTaken = userService.isEmailTaken(registerDTO.getEmail());
        boolean usernameIsTaken = userService.isUsernameTaken(registerDTO.getUsername());

        if (bindingResult.hasErrors() || passwordNotMatch || emailIsTaken || usernameIsTaken) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            log.warn("invalid input");
            return "redirect:/register";
        }
        userService.register(registerDTO);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            log.warn("invalid input");
            return "redirect:/login";
        }
        if (userService.login(loginDTO)) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            log.warn("wrong credentials");
            return "redirect:/login";
        }
    }

}