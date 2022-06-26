package com.example.exam6.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderInfoDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String cat;

}