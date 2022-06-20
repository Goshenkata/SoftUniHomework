package com.example.exam2.controller;

import com.example.exam2.model.dto.RegistrationDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:register";
        }
        System.out.println("cock");
        return "redirect:login";
    }

    @ModelAttribute("registrationDTO")
    public RegistrationDTO registrationDTO() {
        return new RegistrationDTO();
    }
}
