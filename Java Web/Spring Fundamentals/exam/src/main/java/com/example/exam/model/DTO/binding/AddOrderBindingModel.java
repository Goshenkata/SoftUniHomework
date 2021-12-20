package com.example.exam.model.DTO.binding;

import com.example.exam.model.entity.enums.CategoryName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOrderBindingModel {
    @NotNull @Size(min = 3, max = 20) String name;

    @NotNull @Positive BigDecimal price;

    @NotNull CategoryName category;

    @NotNull @Size(min = 5) String description;

    public String getName() {
        return name;
    }

    public AddOrderBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrderBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryName getCategory() {
        return category;
    }

    public AddOrderBindingModel setCategory(CategoryName category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrderBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

}
