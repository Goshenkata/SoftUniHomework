package com.example.demo.repository;

import com.example.demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT max(a.id)FROM Author a")
    Long getMaxId();
    @Query("SELECT min(a.id) FROM Author a")
    Long getMinId();

}
