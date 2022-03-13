package com.example.demo.repository;

import com.example.demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT max(a.id)FROM Author a")
    Long getMaxId();
    @Query("SELECT min(a.id) FROM Author a")
    Long getMinId();
    Set<Author> findAllByFirstNameEndingWith(String firstName);
    @Query("select concat(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) from Author a " +
            "join Book b on a = b.author group by a.id order by sum(b.copies) desc ")
    Set<String> findNumberOfCopiesByAuthor();

}
