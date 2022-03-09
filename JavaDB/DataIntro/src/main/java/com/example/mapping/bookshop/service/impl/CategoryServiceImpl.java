package com.example.mapping.bookshop.service.impl;

import com.example.mapping.bookshop.entities.Category;
import com.example.mapping.bookshop.repository.CategoryRepository;
import com.example.mapping.bookshop.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Set<Category> getRandomCategories() {
        Long min = categoryRepository.getMinId();
        Long max = categoryRepository.getMaxId();
        Random random = new Random();
        Set<Category> categories = new HashSet<>();
        for (int i= 0; i < 3; i++) {
            Category category = categoryRepository.getById(random.nextLong(max) + min);
            categories.add(category);
        }
        return categories;
    }
}
