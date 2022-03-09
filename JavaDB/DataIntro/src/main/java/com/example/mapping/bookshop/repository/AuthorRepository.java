package com.example.mapping.bookshop.repository;

import com.example.mapping.bookshop.entities.Author;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT max(a.id)FROM Author a")
    Long getMaxId();
    @Query("SELECT min(a.id) FROM Author a")
    Long getMinId();
    List<Author> findAllByFirstNameAndLastName(String firstName, String lastName);

}
