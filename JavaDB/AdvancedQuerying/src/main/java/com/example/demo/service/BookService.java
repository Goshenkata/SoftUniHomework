package com.example.demo.service;

import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {
    void save(Book book);
    List<String> getBookTitlesByAgeRestriction(AgeRestriction ageRestriction);
    List<String> getGoldBookTitlesWithLessThat5000Copies();

    List<String> exercise3();

    List<String> getBooksNotReleasedIn(Integer year);

    List<String> exercise5(LocalDate date);
    Set<String> exercise7(String containing);

    Set<String> exercise8(String startingWith);

    int exercise9(int minLenght);

    String exercise11(String title);

    void exercise12(String date, int amount);
}
