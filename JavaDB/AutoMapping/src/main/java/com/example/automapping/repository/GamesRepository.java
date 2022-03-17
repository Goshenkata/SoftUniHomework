package com.example.automapping.repository;

import com.example.automapping.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {
    Optional<Games> findByTitle(String title);
}
