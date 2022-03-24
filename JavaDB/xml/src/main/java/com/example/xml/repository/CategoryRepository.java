package com.example.xml.repository;

import com.example.xml.dto.Qd3DTO;
import com.example.xml.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select min(c.id) from Category c")
    Integer getByMinId();
    @Query("select max(c.id) from Category c")
    Integer getByMaxId();
}
