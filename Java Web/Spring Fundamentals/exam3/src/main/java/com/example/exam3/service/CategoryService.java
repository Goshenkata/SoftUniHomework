package com.example.exam3.service;

import com.example.exam3.model.entity.Category;
import com.example.exam3.model.entity.CategoryEnum;
import com.example.exam3.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategoryFromEnum(int categoryID) {
        CategoryEnum categoryEnum = switch (categoryID) {
            case 0 -> CategoryEnum.BATTLE;
            case 1 -> CategoryEnum.CARGO;
            case 2 -> CategoryEnum.PATROL;
            default -> throw new IllegalStateException("Invalid ordinal for category enum: " + categoryID);
        };
        return categoryRepository.getByName(categoryEnum);
    }
}
