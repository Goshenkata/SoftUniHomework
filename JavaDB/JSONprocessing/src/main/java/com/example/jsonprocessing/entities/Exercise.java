package com.example.jsonprocessing.entities;

import com.example.jsonprocessing.repository.ProductRepository;
import com.example.jsonprocessing.service.CategoryService;
import com.example.jsonprocessing.service.ProductService;
import com.example.jsonprocessing.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class Exercise implements CommandLineRunner {
    UserService userService;
    ProductService productService;
    private CategoryService categoryService;

    public Exercise(UserService userService,
                    ProductService productService,
                    CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        seedProductShop();
        System.out.println("choose query (1-4");
        int query = Integer.parseInt(scanner.nextLine());
        switch (query) {
            case 1:
                productService.shopQ1();
                break;
            case 2:
                productService.shopQ2();
                break;
            case 3:
                categoryService.shopQ3();
                break;
        }
}

    private void seedProductShop() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }

    private void seedCategories() throws FileNotFoundException {
        if (categoryService.isEmpty()) {
            System.out.println("initialising categories");
            categoryService.seedCategories();
        }
    }

    private void seedProducts() throws FileNotFoundException {
        if (productService.isEmpty()) {
            System.out.println("initialising products");
            productService.seedProducts();
        }
    }

    private void seedUsers() throws FileNotFoundException {
        if (userService.isEmpty()) {
            System.out.println("initialising users");
            userService.seedUser();
        }
    }
}
