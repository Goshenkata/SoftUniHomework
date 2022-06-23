package com.example.exam5.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}
