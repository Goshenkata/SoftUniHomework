package com.example.exam5.repository;

import com.example.exam5.model.entity.Classification;
import com.example.exam5.model.entity.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification getClassificationByClassificationName(ClassificationName classificationName);
}
