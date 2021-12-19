package com.example.exam.controllers;

import com.example.exam.model.DTO.LoginBindingModel;
import com.example.exam.model.DTO.UserRegistrationBindingModel;
import com.example.exam.model.entity.UserEntity;
import com.example.exam.service.UserService;
import com.example.exam.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid LoginBindingModel loginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBindingModel", loginBindingModel);
            return "redirect:login";
        }
        UserEntity user = userService.login(userRegistrationBindingModel());
        currentUser.setId(user.getId());
        return "/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationBindingModel userRegistrationBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);
            return "redirect:register";
        }
        //save to database;
        userService.register(userRegistrationBindingModel);
        redirectAttributes.addFlashAttribute("userLoginBindingModel", modelMapper.map(userRegistrationBindingModel, LoginBindingModel.class));
        return "redirect:login";
    }

    @ModelAttribute("userRegistrationBindingModel")
    private UserRegistrationBindingModel userRegistrationBindingModel() {
        return new UserRegistrationBindingModel();
    }
    @ModelAttribute("LoginBindingModel")
    private LoginBindingModel loginBindingModel() {
        return new LoginBindingModel();
    }
}
