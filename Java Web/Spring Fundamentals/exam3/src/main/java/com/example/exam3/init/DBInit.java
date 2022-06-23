package com.example.exam3.init;

import com.example.exam3.model.entity.Category;
import com.example.exam3.model.entity.CategoryEnum;
import com.example.exam3.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DBInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values())
                    .map(c -> mapToCategory(c)).toList();
            categoryRepository.saveAll(categories);
        }
    }

    private Category mapToCategory(CategoryEnum c) {
        Category category = new Category();
        category.setName(c);
        String description = null;
        switch (c) {
            case BATTLE -> description = "Battle ship";
            case CARGO -> description = "Cargo ship";
            case PATROL -> description = "Petrol ship";
        }
        category.setDescription(description);
        return category;
    }
}
