package com.example.jsonprocessing.repository;
import com.example.jsonprocessing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT min(u.id) from User u")
    Integer getMinID();
    @Query("SELECT max(u.id) from User u")
    Integer getMaxID();
}
