package com.example.exam4.controller;

import com.example.exam4.currentUser.CurrentUser;
import com.example.exam4.model.dto.LoginDTO;
import com.example.exam4.model.dto.RegistrationDTO;
import com.example.exam4.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final CurrentUser currentUser;
    private final UserService userService;

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


    @ModelAttribute("registrationDTO")
    public RegistrationDTO registrationDTO() {
        return new RegistrationDTO();
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        boolean match = registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword());
        boolean isUsernameAvailable = userService.isUsernameAvailable(registrationDTO.getUsername());
        boolean isEmailAvailable =  userService.isEmailAvailable(registrationDTO.getEmail());
        if (bindingResult.hasErrors() ||
                !match ||
                !isEmailAvailable ||
                !isUsernameAvailable) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);
            log.warn("bad input");
            return "redirect:register";
        }
        userService.register(registrationDTO);
        log.info("user registered successfully");
        return "redirect:/login";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @PostMapping("login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            log.warn("invalid input");
            return "redirect:login";
        }
        if (!userService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("notExists", true);
            log.warn("invalid user");
            return "redirect:/login";
        }
        currentUser.setUsername(loginDTO.getUsername());
        currentUser.setLoggedIn(true);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser.clear();
        return "redirect:/";
    }
}