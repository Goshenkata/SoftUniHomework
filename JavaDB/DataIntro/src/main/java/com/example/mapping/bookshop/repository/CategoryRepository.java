package com.example.mapping.bookshop.repository;

import com.example.mapping.bookshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT max(a.id) FROM Category a")
    Long getMaxId();
    @Query("SELECT min(a.id) FROM Category a")
    Long getMinId();
}
