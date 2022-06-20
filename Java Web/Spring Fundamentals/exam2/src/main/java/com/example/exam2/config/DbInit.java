package com.example.exam2.config;

import com.example.exam2.model.entity.Category;
import com.example.exam2.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DbInit implements CommandLineRunner {

    public final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        initCategories();
    }

    private void initCategories() {
        if (categoryRepository.count() == 0) {
            Category food = new Category();
            food.setName("Food");
            food.setDescription("something you eat");
            categoryRepository.save(food);

            Category drink = new Category();
            drink.setName("Drink");
            drink.setDescription("wet");
            categoryRepository.save(drink);

            Category household = new Category();
            household.setName("Household");
            household.setDescription("stuff at home");
            categoryRepository.save(household);

            Category other = new Category();
            other.setName("Other");
            other.setDescription("other things");
            categoryRepository.save(other);

        }
    }
}
