package com.example.automapping.config;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDTO {
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[A-Z]+")
    String title;
    @Size(min = 11, max = 11)
    String trailer;
    @Positive
    Long size;
    @Positive
    BigDecimal price;
    @Size(min = 30)
    String description;
    LocalDate releaseDate;
    @Pattern(regexp = "https*:\\/\\/")
    String imageUrl;

    public GameDTO() {
    }

    public String getTitle() {
        return title;
    }

    public GameDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTrailer() {
        return trailer;
    }

    public GameDTO setTrailer(String trailer) {
        this.trailer = trailer;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public GameDTO setSize(Long size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public GameDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
