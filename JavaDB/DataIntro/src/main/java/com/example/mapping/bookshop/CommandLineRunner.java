package com.example.mapping.bookshop;

import com.example.mapping.bookshop.entities.*;
import com.example.mapping.bookshop.repository.BookRepository;
import com.example.mapping.bookshop.service.AuthorService;
import com.example.mapping.bookshop.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String BOOKS_FILE_NAME = "books.txt";
    private static final String CATEGORIES_FILE_NAME = "categories.txt";
    private static final String AUTHORS_FILE_NAME = "authors.txt";

    AuthorService authorService;
    CategoryService categoryService;
    BookRepository bookRepository;

    public CommandLineRunner(AuthorService authorService, CategoryService categoryService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        query1();
        System.out.println("######################");
        query2();
        System.out.println("######################");
        query3();
        System.out.println("######################");
        query4();

    }

    private void query4() {
        System.out.println("Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.");
        List<Author> authors = authorService.findByName("George", "Powell");
        authors.forEach(author -> {
            System.out.println("author with id: " + author.getId());
            bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author)
                    .forEach(book -> System.out.printf("%s %s %s%n",
                            book.getTitle(),
                            book.getReleaseDate().format(DateTimeFormatter.
                                    ofPattern("dd-MMM-yyyy")),
                            book.getCopies()));
        });

    }

    @Transactional
    void query3() {
        System.out.println("Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.");
        authorService.getAuthorsOrderedByBookCount()
                .forEach(author -> System.out.printf("%s %s %d%n",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()));
    }

    private void query2() {
        System.out.println("Get all authors with at least one book with release date before 1990. Print their first name and last name.");
        authorService.getAllWithOneBookBefore1990()
                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
    }

    private void query1() {
        System.out.println("Get all books after the year 2000. Print only their titles.");
        bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000, 1, 1))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void seedDatabase() throws IOException {
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

                    bookRepository.save(book);
                });
    }
}
