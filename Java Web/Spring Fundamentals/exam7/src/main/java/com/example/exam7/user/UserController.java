package com.example.exam7.user;

import com.example.exam7.model.dto.LoginDto;
import com.example.exam7.model.dto.RegisterDTO;
import com.example.exam7.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

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

    @ModelAttribute("loginDTO")
    public LoginDto loginDto() {
        return new LoginDto();

    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("usernameInvalid", bindingResult.getFieldErrors("username").isEmpty());
            redirectAttributes.addFlashAttribute("passwordInvalid", bindingResult.getFieldErrors("password").isEmpty());
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            return "redirect:/login";
        }
        if (userService.login(loginDto)) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            return "redirect:/login";
        }
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }
    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
            !registerDTO.getPassword().equals(registerDTO.getConfirmPassword()) ||
            userService.isEmailTaken(registerDTO.getEmail()) ||
                userService.isUsernameTaken(registerDTO.getPassword())
        ) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            log.warn("invalid input");
            return "redirect:/register";
        }
        userService.register(registerDTO);
        return "redirect:/login";
    }
}