package com.example.exam5.repository;

import com.example.exam5.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.table.TableStringConverter;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByName(String name);
}
