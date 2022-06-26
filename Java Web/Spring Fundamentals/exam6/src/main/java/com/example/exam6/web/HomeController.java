package com.example.exam6.web;

import com.example.exam6.service.OrderService;
import com.example.exam6.service.UserService;
import com.example.exam6.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Currency;

@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        if (currentUser.isLoggedIn()) {
            model.addAttribute("ordersInfo", orderService.getOrdersInfo());
            model.addAttribute("employeesInfo", userService.getEmployeeInfo());
            model.addAttribute("sumTime", orderService.getTimeSum());
            return "home";
        } else{
            return "index";
        }
    }
}
