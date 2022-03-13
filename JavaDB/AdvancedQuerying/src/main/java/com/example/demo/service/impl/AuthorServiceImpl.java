package com.example.demo.service.impl;


import com.example.demo.entities.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.persistence.Temporal;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    public Set<String> exercise6(String firstName) {
        return authorRepository
                .findAllByFirstNameEndingWith(firstName)
                .stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> exercise10() {
        return authorRepository.findNumberOfCopiesByAuthor();
    }
}
