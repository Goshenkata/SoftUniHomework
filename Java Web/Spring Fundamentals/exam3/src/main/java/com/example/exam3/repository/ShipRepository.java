package com.example.exam3.repository;

import com.example.exam3.model.entity.Ship;
import com.example.exam3.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findAllByUser(User user);
    List<Ship> findAllByUserNot(User user);
    Ship getShipByName(String name);
}
