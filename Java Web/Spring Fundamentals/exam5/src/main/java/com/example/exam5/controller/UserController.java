package com.example.exam5.controller;

import com.example.exam5.model.dto.LoginDTO;
import com.example.exam5.model.dto.RegistrationDTO;
import com.example.exam5.service.UserService;
import com.example.exam5.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;

    @GetMapping("/register")
    public String register() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @ModelAttribute("registrationDTO")
    public RegistrationDTO registrationDTO() {
        return new RegistrationDTO();
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);
            return "redirect:/register";
        }
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordsMatch", true);
            return "redirect:register";
        }
        if (userService.isUsernameTaken(registrationDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:/register";
        }

        if (userService.isEmailTaken(registrationDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("emailTaken", true);
            return "redirect:/register";
        }
        userService.register(registrationDTO);
        return "redirect:/login";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }


    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        boolean emailIsEmpty = loginDTO.getEmail().isBlank();
        int passwordLength = loginDTO.getPassword().length();
        boolean passwordIsValid =  passwordLength >= 3 && passwordLength <= 20;
        if (emailIsEmpty || !passwordIsValid) {
            redirectAttributes.addFlashAttribute("emailIsEmpty", emailIsEmpty);
            redirectAttributes.addFlashAttribute("passwordIsValid", !passwordIsValid);
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            return "redirect:login";
        }
        if (userService.login(loginDTO)) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("incorrectData", true);
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser.clear();
        return "redirect:/";
    }

}