package com.example.exam6.web;

import ch.qos.logback.core.pattern.color.RedCompositeConverter;
import com.example.exam6.model.dto.AddOrderDTO;
import com.example.exam6.model.entity.CategoryName;
import com.example.exam6.service.OrderService;
import com.example.exam6.user.CurrentUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final CurrentUser currentUser;
    private final OrderService orderService;

    @GetMapping("/add")
    public String add() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "order-add";
    }

    @ModelAttribute("categories")
    public List<CategoryName> categoryNames() {
        return Arrays.stream(CategoryName.values()).toList();
    }

    @ModelAttribute("addOrderDTO")
    public AddOrderDTO addOrderDTO() {
        return new AddOrderDTO();
    }

    @PostMapping("/add")
    public String add(@Valid AddOrderDTO addOrderDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        System.out.println(addOrderDTO);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", bindingResult);
            log.warn("wrong input");
            return "redirect:/add";
        }
        orderService.add(addOrderDTO);
        return "redirect:/";
    }

    @PostMapping("/ready/{id}")
    public String ready(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/";
    }
}