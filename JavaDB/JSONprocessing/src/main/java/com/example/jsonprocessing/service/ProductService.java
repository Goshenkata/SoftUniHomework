package com.example.jsonprocessing.service;

import com.example.jsonprocessing.DTO.ProductDTO;
import com.example.jsonprocessing.DTO.Q2DTO;
import com.example.jsonprocessing.DTO.Q2DTOProduct;
import com.example.jsonprocessing.entities.Product;
import com.example.jsonprocessing.repository.ProductRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {
    private static final String PRODUCT_JSON_PATH = "src/main/resources/09. DB-Advanced-JSON-Processing-Exercises/products.json";
    ProductRepository productRepository;
    Gson gson;
    ModelMapper mapper;
    UserService userService;
    CategoryService categoryService;

    public ProductService(ProductRepository productRepository,
                          Gson gson,
                          ModelMapper mapper,
                          UserService userService,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public boolean isEmpty() {
        return productRepository.count() == 0;
    }

    @Transactional
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCT_JSON_PATH);
        ProductDTO[] productDTO = gson.fromJson(fileReader, ProductDTO[].class);
        List<Product> products = Arrays.stream(productDTO)
                .map(this::mapToEnt)
                .toList();
        productRepository.saveAll(products);
    }

    private Product mapToEnt(ProductDTO p) {
        Product product =  mapper.map(p, Product.class);
        product.setSeller(userService.randomUser());
        Random random = new Random();
        if (random.nextBoolean()) {
            product.setBuyer(userService.randomUser());
        } else {
            product.setBuyer(null);
        }
        product.setCategories(categoryService.getRandomCategories());
        return product;
    }

    public void shopQ1() {
        String s = gson.toJson(productRepository.shopQ1());
        System.out.println(s);
    }

    public void shopQ2() {
        List<Q2DTO> q2DTOS = productRepository.shopQ2();
        for (int i = 0; i < q2DTOS.size(); i++) {
            String firstName = q2DTOS.get(i).getFirstName();
            String lastName = q2DTOS.get(i).getLastName();
            List<Q2DTOProduct> products = productRepository.shopQ2Product(firstName, lastName);
            q2DTOS.get(i).setSoldProducts(products);
        }
        String s = gson.toJson(q2DTOS);
        System.out.println(s);
    }
}
