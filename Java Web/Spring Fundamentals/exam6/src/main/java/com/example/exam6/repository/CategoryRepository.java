package com.example.exam6.repository;

import com.example.exam6.model.entity.Category;
import com.example.exam6.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryName name);
}
