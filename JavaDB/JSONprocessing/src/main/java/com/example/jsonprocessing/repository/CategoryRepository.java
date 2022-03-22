package com.example.jsonprocessing.repository;

import com.example.jsonprocessing.DTO.Q3DTO;
import com.example.jsonprocessing.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select min(c.id) from Category c")
    Integer getByMinId();
    @Query("select max(c.id) from Category c")
    Integer getByMaxId();
    @Query("select new com.example.jsonprocessing.DTO.Q3DTO(c.name, c.products.size, c) from Category c order by c.products.size")
    List<Q3DTO> q3();
}
