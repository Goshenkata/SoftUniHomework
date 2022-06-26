package com.example.exam7.web;

import com.example.exam7.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;

    @GetMapping("")
    public String home() {
        if (currentUser.isLoggedIn()) {
            return "home";
        }
        return "index";
    }

}
