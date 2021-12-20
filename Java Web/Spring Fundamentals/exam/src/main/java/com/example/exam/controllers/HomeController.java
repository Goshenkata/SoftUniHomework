package com.example.exam.controllers;

import com.example.exam.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String home() {
        System.out.println("im in");
        if (currentUser.getId()==null) {
            System.out.println("index");
            return "index";
        }
        System.out.println("home");
        return "home";
    }

}
