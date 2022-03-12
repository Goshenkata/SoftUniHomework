package com.example.demo;


import com.example.demo.entities.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Exercise implements CommandLineRunner {

    AuthorService authorService;
    CategoryService categoryService;
    BookService bookService;

    public Exercise(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String BOOKS_FILE_NAME = "books.txt";
    private static final String CATEGORIES_FILE_NAME = "categories.txt";
    private static final String AUTHORS_FILE_NAME = "authors.txt";

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        Scanner scanner = new Scanner(System.in);
        System.out.println("pick an exercise (1-12)");
        int exercise = Integer.parseInt(scanner.nextLine());
        switch (exercise) {
            case 1:
                exercise1();
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    private void exercise1() {

    }

    public void seedDatabase() throws IOException {
        seedCategories();
        seedActors();
        seedBooks();
    }

    private void seedActors() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME))
                .forEach(row -> {
                    Author author = new Author();
                    String[] names = row.split(" ");
                    author.setFirstName(names[0]);
                    author.setLastName(names[1]);
                    authorService.save(author);
                });
    }

    private void seedCategories() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORIES_FILE_NAME))
                .forEach(row -> {
                    Category category = new Category();
                    category.setName(row);
                    categoryService.save(category);
                });
    }

    private void seedBooks() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();


                    Book book = new Book(title, editionType, price, releaseDate,
                            ageRestriction, author, categories, copies);

                    bookService.save(book);
                });
    }

}
