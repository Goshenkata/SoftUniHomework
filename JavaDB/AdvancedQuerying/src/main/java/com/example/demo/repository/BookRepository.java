package com.example.demo.repository;


import com.example.demo.entities.AgeRestriction;
import com.example.demo.entities.Book;
import com.example.demo.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByCopiesLessThanAndEditionType(Integer copies, EditionType editionType);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);
    @Query("select b from Book b where year(b.releaseDate) != ?1")
    List<Book> findAllByReleaseDateYear(int releaseDate_year);
    List<Book> findAllByReleaseDateBefore(LocalDate date);
    Set<Book> findByTitleContainingIgnoreCase(String title);
    Set<Book> findAllByAuthorLastNameIgnoreCaseStartingWith(String startingWith);
    @Query("select count(b.id) from Book b where length(b.title) > ?1")
    Integer countBooksByTitleLengthGreaterThan(int length);
    @Query("select concat(b.title, ' ', b.editionType, ' ', b.ageRestriction, ' ', b.price)" +
            " from Book b where b.title like ?1")
    String bookInfoByTitle(String title);
    @Transactional
    @Modifying
    @Query("update Book b set b.copies = b.copies + ?2 where b.releaseDate > ?1")
    int increaseAfterDate(LocalDate date, int amount);
}
