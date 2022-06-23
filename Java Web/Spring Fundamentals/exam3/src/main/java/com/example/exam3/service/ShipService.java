package com.example.exam3.service;

import com.example.exam3.model.dto.FightDTO;
import com.example.exam3.model.dto.ShipDTO;
import com.example.exam3.model.entity.Category;
import com.example.exam3.model.entity.Ship;
import com.example.exam3.model.entity.User;
import com.example.exam3.repository.CategoryRepository;
import com.example.exam3.repository.ShipRepository;
import com.example.exam3.repository.UserReporitory;
import com.example.exam3.user.CurrentUser;
import com.example.exam3.web.ShipStatsDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    public void addShip(ShipDTO shipDTO) {
        Ship ship = mapper.map(shipDTO, Ship.class);
        String username = currentUser.getUsername();
        User user = userService
                .findByUsername(username);
        ship.setUser(user);
        Category category = categoryService.getCategoryFromEnum(shipDTO.getCategory());
        ship.setCategory(category);
        shipRepository.save(ship);
    }

    public List<ShipStatsDTO> getAllShips() {
        return shipRepository.findAll()
                .stream()
                .map(s -> mapper.map(s, ShipStatsDTO.class))
                .toList();
    }

    public List<String> getCurrentUserShips() {
        return shipRepository.findAllByUser(userService.getCurrentUser())
                .stream().map(Ship::getName)
                .toList();
    }

    public List<String> getOtherUserShips() {
        return shipRepository.findAllByUserNot(userService.getCurrentUser())
                .stream().map(Ship::getName)
                .toList();
    }

    @Transactional
    public void fight(FightDTO fightDTO) {
        Ship attacker = shipRepository.getShipByName(fightDTO.getAttacker());
        Ship defender = shipRepository.getShipByName(fightDTO.getDefender());
        defender.setHealth(defender.getHealth() - attacker.getPower());
        if (defender.getHealth()<=0) {
            shipRepository.delete(defender);
        } else {
            shipRepository.save(defender);
        }
    }
}
