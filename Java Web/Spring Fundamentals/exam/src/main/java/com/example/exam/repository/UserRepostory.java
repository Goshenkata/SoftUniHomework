package com.example.exam.repository;

import com.example.exam.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepostory extends JpaRepository<UserEntity, Long> {

    UserEntity getByUsernameAndPassword(String username, String password);
}
