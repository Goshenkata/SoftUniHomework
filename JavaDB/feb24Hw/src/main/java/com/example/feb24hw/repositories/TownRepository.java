package com.example.feb24hw.repositories;

import com.example.feb24hw.entities.Town;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findAllByName(String name);
}
