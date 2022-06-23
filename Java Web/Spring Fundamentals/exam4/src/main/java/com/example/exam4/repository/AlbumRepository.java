package com.example.exam4.repository;

import com.example.exam4.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("SELECT sum(a.copies) from Album a")
    Long getTotalCopies();
}
