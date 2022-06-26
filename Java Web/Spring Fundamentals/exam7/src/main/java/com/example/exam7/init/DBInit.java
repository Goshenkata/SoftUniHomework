package com.example.exam7.init;

import com.example.exam7.model.enitity.Category;
import com.example.exam7.model.enitity.CategoryName;
import com.example.exam7.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@AllArgsConstructor
public class DBInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryName.values()).map(Category::new).toList();
            categoryRepository.saveAll(categories);
        }
    }
}
