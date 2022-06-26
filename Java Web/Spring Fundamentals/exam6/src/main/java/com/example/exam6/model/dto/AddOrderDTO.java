package com.example.exam6.model.dto;

import com.example.exam6.model.entity.CategoryName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class AddOrderDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;
    @NotNull
    private CategoryName categoryName;
    @NotBlank
    @Size(min = 3)
    private String description;
}
