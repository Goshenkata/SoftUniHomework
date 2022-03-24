package com.example.xml;

import com.example.xml.entities.Category;
import com.example.xml.service.CategoryService;
import com.example.xml.service.ProductService;
import com.example.xml.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import java.util.Scanner;

@Component
public class Exercise implements CommandLineRunner {

    UserService userService;
    CategoryService categoryService;
    ProductService productService;

    public Exercise(UserService userService,
                    CategoryService categoryService,
                    ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter query number");
        int query = Integer.parseInt(scanner.nextLine());
        switch (query) {
            case 1 -> productService.q1();
            case 2 -> userService.q2();
            case 3 -> categoryService.q3();
            case 4 -> userService.q4();
            default -> throw new IllegalArgumentException("invalid query number");
        }
    }
}
