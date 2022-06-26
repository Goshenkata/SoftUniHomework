package com.example.softuniexam.web;

import com.example.softuniexam.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;

    @GetMapping("/")
    public String home() {
        if (currentUser.isLoggedIn()) {
            return "home";
        } else{
            return "index";
        }
    }
}