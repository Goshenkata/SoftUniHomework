package com.example.exam3.web;

import com.example.exam3.model.dto.FightDTO;
import com.example.exam3.model.dto.ShipDTO;
import com.example.exam3.model.entity.Ship;
import com.example.exam3.service.ShipService;
import com.example.exam3.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ship")
@AllArgsConstructor
public class ShipController {
    private final CurrentUser currentUser;
    private final ShipService shipService;
    @GetMapping("/add")
    public String addShip() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "ship-add";
    }





    @PostMapping("/add")
    public String addShip(@Valid ShipDTO shipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!currentUser.isLoggedIn()) {
        return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDTO", bindingResult);
            return "redirect:/ship/add";
        }
        shipService.addShip(shipDTO);
        return "redirect:/";
    }

}
