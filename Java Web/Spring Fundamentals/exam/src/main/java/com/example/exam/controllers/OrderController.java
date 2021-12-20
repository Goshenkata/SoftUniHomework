package com.example.exam.controllers;

import com.example.exam.model.DTO.binding.AddOrderBindingModel;
import com.example.exam.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid AddOrderBindingModel addOrderBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrderBindingModel");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderBindingModel", bindingResult);
            return "redirect:add";
        }
        orderService.add(addOrderBindingModel);
        return "redirect:/";
    }

    @ModelAttribute("addOrderBindingModel")
    public AddOrderBindingModel addOrderBindingModel() {
        return new AddOrderBindingModel();
    }
}
