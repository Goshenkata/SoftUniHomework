package com.example.automapping.repository;

import com.example.automapping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailContaining(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
