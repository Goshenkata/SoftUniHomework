package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.StyleName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
public class SongAddDTO {
    @NotNull
    @Size(min = 3, max = 20)
    private String performer;
    @NotNull
    @Size(min = 2, max = 20)
    private String title;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @NotNull
    @Positive
    private Integer duration;
    @NotNull
    private StyleName styleName;
}
