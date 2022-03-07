package com.example.feb24hw.repositories;

import com.example.feb24hw.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    List<Project> findByOrderByStartDateDesc();
}
