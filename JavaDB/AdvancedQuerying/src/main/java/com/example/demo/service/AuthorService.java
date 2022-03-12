package com.example.demo.service;


import com.example.demo.entities.Author;

public interface AuthorService {
    Author getRandomAuthor();

    void save(Author author);
}
