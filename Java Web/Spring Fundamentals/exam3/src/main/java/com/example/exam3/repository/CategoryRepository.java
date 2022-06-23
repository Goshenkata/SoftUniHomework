package com.example.exam3.repository;

import com.example.exam3.model.entity.Category;
import com.example.exam3.model.entity.CategoryEnum;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Category> {
    Category getByName(CategoryEnum name);
}