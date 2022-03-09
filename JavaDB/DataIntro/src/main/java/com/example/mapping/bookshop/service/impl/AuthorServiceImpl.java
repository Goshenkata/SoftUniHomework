package com.example.mapping.bookshop.service.impl;

import com.example.mapping.bookshop.entities.Author;
import com.example.mapping.bookshop.entities.Book;
import com.example.mapping.bookshop.repository.AuthorRepository;
import com.example.mapping.bookshop.repository.BookRepository;
import com.example.mapping.bookshop.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author getRandomAuthor() {
        Long min = authorRepository.getMinId();
        Long max = authorRepository.getMaxId();
        Random random = new Random();
        return authorRepository.getById(random.nextLong(max) + min);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public Set<Author> getAllWithOneBookBefore1990() {
        Set<Author> authors = new HashSet<>();
        Set<Book> books = bookRepository.findAllByReleaseDateBefore(LocalDate.of(1990, 1, 1));
        books.forEach(book -> authors.add(book.getAuthor()));
        return authors;
    }

    @Override
    @Transactional
    public Set<Author> getAuthorsOrderedByBookCount() {
        return authorRepository.findAll()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(),a1.getBooks().size()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public List<Author> findByName(String firstName, String lastName) {
        return authorRepository.findAllByFirstNameAndLastName(firstName,lastName);
    }
}
