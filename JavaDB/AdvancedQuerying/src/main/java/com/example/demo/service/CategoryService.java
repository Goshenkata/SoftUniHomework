package com.example.demo.service;


import com.example.demo.entities.Category;

import java.util.Set;

public interface CategoryService {

    void save(Category category);
    Set<Category> getRandomCategories();
}
