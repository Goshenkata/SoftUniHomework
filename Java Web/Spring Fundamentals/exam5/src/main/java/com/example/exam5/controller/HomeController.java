package com.example.exam5.controller;

import com.example.exam5.service.TaskService;
import com.example.exam5.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;
    private final TaskService tastService;

    @GetMapping("/")
    public String home(Model model) {
        if (currentUser.isLoggedIn()) {
            model.addAttribute("tasks", tastService.getAllTasks());
            return "home";
        }
        return "index";
    }
}
