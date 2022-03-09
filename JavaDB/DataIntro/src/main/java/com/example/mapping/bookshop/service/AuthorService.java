package com.example.mapping.bookshop.service;


import com.example.mapping.bookshop.entities.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Author getRandomAuthor();

    void save(Author author);

    Set<Author> getAllWithOneBookBefore1990();

    Set<Author> getAuthorsOrderedByBookCount();
    List<Author> findByName(String firstName, String lastName);
}
