package com.example.exam.repository;

import com.example.exam.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostory extends JpaRepository<UserEntity, Long> {
    UserEntity getElementByUsernameAndPassword();
}
