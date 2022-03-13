package com.example.demo.service.impl;

import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.Book;
import com.example.demo.entities.EditionType;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<String> getBookTitlesByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.
                findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getGoldBookTitlesWithLessThat5000Copies() {
        return bookRepository
                .findAllByCopiesLessThanAndEditionType(5000, EditionType.GOLD)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> exercise3() {
        return bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(
                        BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .stream()
                .map(book -> book.getTitle() + " - " + book.getPrice())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksNotReleasedIn(Integer year) {
        return bookRepository
                .findAllByReleaseDateYear(year)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> exercise5(LocalDate date) {
        return bookRepository
                .findAllByReleaseDateBefore(date)
                .stream()
                .map(b -> String.format("%s %s %.2f", b.getTitle(), b.getAgeRestriction().name(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> exercise7(String containing) {
        return bookRepository
                .findByTitleContainingIgnoreCase(containing)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> exercise8(String startingWith) {
        return bookRepository
                .findAllByAuthorLastNameIgnoreCaseStartingWith(startingWith)
                .stream()
                .map(b -> String.format("%s (%s %s)",
                        b.getTitle(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()))
                .collect(Collectors.toSet());
    }

    @Override
    public int exercise9(int minLength) {
        return bookRepository
                .countBooksByTitleLengthGreaterThan(minLength);
    }

    @Override
    public String exercise11(String title) {
        return bookRepository.bookInfoByTitle(title);
    }

    @Override
    public void exercise12(String date, int amount) {
        String[] dateParts = date.split(" ");
        int year = Integer.parseInt(dateParts[2]);
        int month = switch (dateParts[1].toLowerCase(Locale.ROOT)) {
            case "jan" -> 1;
            case "feb" -> 2;
            case "mar" -> 3;
            case "apr" -> 4;
            case "may" -> 5;
            case "jun" -> 6;
            case "jul" -> 7;
            case "aug" -> 8;
            case "sep" -> 9;
            case "oct" -> 10;
            case "nov" -> 11;
            case "dec" -> 12;
            default -> throw new IllegalArgumentException("invalid month for date");
        };
        int day = Integer.parseInt(dateParts[0]);
        bookRepository.increaseAfterDate(LocalDate.of(year,month,day),amount);
    }
}
