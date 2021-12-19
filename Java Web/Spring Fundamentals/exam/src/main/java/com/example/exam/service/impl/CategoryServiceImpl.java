package com.example.exam.service.impl;

import com.example.exam.model.entity.CategoryEntity;
import com.example.exam.model.entity.enums.CategoryName;
import com.example.exam.repository.CategoryRepository;
import com.example.exam.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.findAll().size() == 0) {
            for (CategoryName categoryName : CategoryName.values()) {
                CategoryEntity category = new CategoryEntity();
                category.setName(categoryName);
                switch (categoryName) {
                    case COFFEE:
                        category.setTimeNeeded(2);
                        break;
                    case CAKE:
                        category.setTimeNeeded(10);
                        break;
                    case DRINK:
                        category.setTimeNeeded(1);
                        break;
                    case OTHER:
                        category.setTimeNeeded(5);
                        break;
                }
                categoryRepository.save(category);
            }
        }
    }
}
