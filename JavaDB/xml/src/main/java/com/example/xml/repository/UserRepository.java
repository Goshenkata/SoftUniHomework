package com.example.xml.repository;

import com.example.xml.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT min(u.id) from User u")
    Integer getMinID();
    @Query("SELECT max(u.id) from User u")
    Integer getMaxID();
    @Query("SELECT u from User u where u.productsSold.size >= 1 order by u.lastName, u.firstName")
    List<User> q2();

    @Query("SELECT u from User u where u.productsSold.size >= 1 order by u.productsSold.size desc , u.lastName")
    List<User> q4();
}
