package com.example.exam4.model.dto;

import com.example.exam4.model.entity.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRenderer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class AlbumAddDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Size(min = 5)
    private String imageURL;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @Positive
    private int copies;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String producer;
    @NotBlank
    private String artistName;
    @NotNull
    private Genre genre;
    @Size(min = 5)
    private String description;
}
