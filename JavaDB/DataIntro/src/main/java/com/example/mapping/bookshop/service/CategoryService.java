package com.example.mapping.bookshop.service;

import com.example.mapping.bookshop.entities.Category;

import java.util.Set;

public interface CategoryService {

    void save(Category category);
    Set<Category> getRandomCategories();
}
