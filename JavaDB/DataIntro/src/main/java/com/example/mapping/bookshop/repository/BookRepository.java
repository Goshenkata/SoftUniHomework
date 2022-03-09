package com.example.mapping.bookshop.repository;

import com.example.mapping.bookshop.entities.Author;
import com.example.mapping.bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findAllByReleaseDateAfter(LocalDate date);
    Set<Book> findAllByReleaseDateBefore(LocalDate date);
    Set<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
