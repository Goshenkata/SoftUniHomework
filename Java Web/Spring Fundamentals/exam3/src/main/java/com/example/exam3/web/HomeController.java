package com.example.exam3.web;

import com.example.exam3.model.dto.FightDTO;
import com.example.exam3.model.dto.ShipDTO;
import com.example.exam3.service.ShipService;
import com.example.exam3.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    @GetMapping("/")
    public String home(Model model) {
        if (currentUser.isLoggedIn()) {
            List<ShipStatsDTO> shipStatsDTOList = shipService.getAllShips();
            model.addAttribute("ships", shipStatsDTOList);
            model.addAttribute("currentUserShips", shipService.getCurrentUserShips());
            model.addAttribute("otherUserShips", shipService.getOtherUserShips());
            return "home";
        }
        return "index";
    }

    @ModelAttribute("shipDTO")
    public ShipDTO shipDTO() {
        return new ShipDTO();
    }


    @ModelAttribute("fightDTO")
    public FightDTO fightDTO() {
        return new FightDTO();
    }

    @PostMapping("/fight")
    public String fight(@Valid FightDTO fightDTO,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("ship not selected!");
            return "redirect:/";
        }

        shipService.fight(fightDTO);
        return "redirect:/";
    }
}