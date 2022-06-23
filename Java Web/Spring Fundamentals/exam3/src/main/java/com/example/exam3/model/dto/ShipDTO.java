package com.example.exam3.model.dto;

import com.example.exam3.model.entity.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShipDTO {
    @Size(min = 2, max = 10)
    private String name;
    @Positive
    private Long health;
    @Positive
    private Long power;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate created;
    @PositiveOrZero
    int category = -1;
}