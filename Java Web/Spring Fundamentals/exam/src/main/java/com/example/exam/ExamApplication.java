package com.example.exam;

import com.example.exam.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner {

    CategoryService categoryService;
    public ExamApplication(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
    }
}
