package com.example.exam6.init;

import com.example.exam6.model.entity.Category;
import com.example.exam6.model.entity.CategoryName;
import com.example.exam6.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DBInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryName categoryName : CategoryName.values()) {
            Category category = new Category();
            category.setName(categoryName);
            int neededTime = 0;
            switch (categoryName) {
                case COFFEE -> neededTime = 2;
                case CAKE -> neededTime = 10;
                case DRINK -> neededTime = 1;
                case OTHER -> neededTime = 5;
            }
            category.setNeededTime(neededTime);
            categoryList.add(category);
        }
        categoryRepository.saveAll(categoryList);
        }
    }
}
