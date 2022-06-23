package com.example.exam3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Size(min = 3, max = 10)
    private String username;
    @Size(min = 3)
    private String password;
}
