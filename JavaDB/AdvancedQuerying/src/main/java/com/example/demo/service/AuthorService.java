package com.example.demo.service;


import com.example.demo.entities.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Author getRandomAuthor();

    void save(Author author);

    Set<String> exercise6(String name);

    Set<String> exercise10();
}
