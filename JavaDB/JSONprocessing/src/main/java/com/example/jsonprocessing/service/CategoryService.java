package com.example.jsonprocessing.service;

import com.example.jsonprocessing.DTO.CategoryDTO;
import com.example.jsonprocessing.DTO.Q3DTO;
import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.repository.CategoryRepository;
import com.example.jsonprocessing.repository.ProductRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final String CATEGORIES_JSON_PATH = "src/main/resources/09. DB-Advanced-JSON-Processing-Exercises/categories.json";
    CategoryRepository categoryRepository;
    Gson gson;
    ModelMapper modelMapper;
    ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, Gson gson, ModelMapper modelMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public boolean isEmpty() {
        return categoryRepository.count() == 0;
    }

    @Transactional
    public void seedCategories() throws FileNotFoundException {
        FileReader reader = new FileReader(CATEGORIES_JSON_PATH);
        CategoryDTO[] categoryDTOS = gson.fromJson(reader, CategoryDTO[].class);
        List<Category> categories = Arrays.stream(categoryDTOS).map(c -> modelMapper.map(c, Category.class)).toList();
        categoryRepository.saveAll(categories);
    }

    public Set<Category> getRandomCategories() {
        Random random = new Random();
        int n = random.nextInt(6) + 1;
        Set<Category> categories = new HashSet<>();
        Integer minId = categoryRepository.getByMinId();
        Integer maxId = categoryRepository.getByMaxId();
        for (int i = 0; i < n; i++) {
            Long randomId = random.nextLong(maxId) + minId;
            categories.add(categoryRepository.getById(randomId));
        }
        return categories;
    }

    public void shopQ3() {
        List<Q3DTO> q3DTOS = categoryRepository.q3();
        for (int i = 0; i < q3DTOS.size(); i++) {
            Category category = q3DTOS.get(i).getCategoryEnt();
            Double averagePrice = productRepository.getAvgForCategory(category);
            Double total = productRepository.getTotalForCategory(category);
            q3DTOS.get(i).setAveragePrice(averagePrice);
            q3DTOS.get(i).setTotalRevenue(total);
        }
        String s = gson.toJson(q3DTOS);
        System.out.println(s);
    }
}
