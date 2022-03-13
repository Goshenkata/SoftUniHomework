package com.example.demo;


import com.example.demo.entities.*;
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
import java.util.Locale;
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
            case 1 -> exercise1();
            case 2 -> exercise2();
            case 3 -> exercise3();
            case 4 -> exercise4();
            case 5 -> exercise5();
            case 6 -> exercise6();
            case 7 -> exercise7();
            case 8 -> exercise8();
            case 9 -> exercise9();
            case 10 -> exercise10();
            case 11 -> exercise11();
            case 12 -> exercise12();
            default -> System.out.println("Wrong input");
        }
    }

    private void exercise12() {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int amount = Integer.parseInt(scanner.nextLine());
        bookService.exercise12(date, amount);

    }

    private void exercise11() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println(bookService.exercise11(title));
    }

    private void exercise10() {
        authorService
                .exercise10()
                .forEach(System.out::println);
    }

    private void exercise9() {
        Scanner scanner = new Scanner(System.in);
        int minLength = Integer.parseInt(scanner.nextLine());
        System.out.println(bookService.exercise9(minLength));
    }

    private void exercise8() {
        Scanner scanner = new Scanner(System.in);
        bookService
                .exercise8(scanner.nextLine())
                .forEach(System.out::println);
    }

    private void exercise7() {
        Scanner scanner = new Scanner(System.in);
        bookService
                .exercise7(scanner.nextLine())
                .forEach(System.out::println);
    }

    private void exercise6() {
        Scanner scanner = new Scanner(System.in);
        authorService
                .exercise6(scanner.nextLine())
                .forEach(System.out::println);
    }

    private void exercise5() {
        Scanner scanner = new Scanner(System.in);
        int[] date = Arrays.stream(scanner.nextLine().split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        bookService
                .exercise5(LocalDate.of(date[2], date[1], date[0]))
                .forEach(System.out::println);
    }

    private void exercise4() {
        Scanner scanner = new Scanner(System.in);
        Integer year = Integer.valueOf(scanner.nextLine());
        bookService.getBooksNotReleasedIn(year)
                .forEach(System.out::println);

    }

    private void exercise3() {
        bookService.
                exercise3()
                .forEach(System.out::println);
    }

    private void exercise2() {
        bookService
                .getGoldBookTitlesWithLessThat5000Copies()
                .forEach(System.out::println);
    }

    private void exercise1() {
        Scanner scanner = new Scanner(System.in);
        String restrictionString = scanner.nextLine().toUpperCase(Locale.ROOT);

        AgeRestriction ageRestriction = switch (restrictionString) {
            case "TEEN" -> AgeRestriction.TEEN;
            case "ADULT" -> AgeRestriction.ADULT;
            case "MINOR" -> AgeRestriction.MINOR;
            default -> throw new IllegalArgumentException("Invalid input for age restrinction");
        };

        bookService
                .getBookTitlesByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
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
