package com.example.exam4.model.dto;

import com.example.exam4.model.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumStatDTO {
    private String imageURL;
    private String name;
    private String artistName;
    private Genre genre;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Integer copies;
    private Long id;
}
