package com.example.exam2.model.dto;

import com.example.exam2.model.validation.FieldMatch;
import com.example.exam2.model.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
public class RegistrationDTO {
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @UniqueUsername
    String username;
    @NotNull(message = "Email cannot be empty!")
    @Email(message = "Invalid email!")
    String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    String password;
    String confirmPassword;
}
