package com.example.exam3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    @Size(min = 3, max = 10)
    String username;
    @Size(min = 5, max = 20)
    String fullname;
    @Pattern(regexp = ".+@.+")
    String email;
    @Size(min = 3)
    String password;
    @Size(min = 3)
    String confirmPassword;
}
