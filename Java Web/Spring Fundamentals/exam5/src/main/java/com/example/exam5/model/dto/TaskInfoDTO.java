package com.example.exam5.model.dto;

import com.example.exam5.model.entity.Classification;
import com.example.exam5.model.entity.ClassificationName;
import com.example.exam5.model.entity.Progress;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskInfoDTO {
    private Long id;
    private String name;
    private String assignedToUsername;
    private ClassificationName classificationClassificationName;
    private LocalDate dueDate;
    private Progress progress;
}
