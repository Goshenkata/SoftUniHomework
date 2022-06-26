package com.example.spotifyplaylistapp.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Pattern(regexp = ".*@.*")
    private String email;
    @NotBlank
    @Size(min = 3, max = 20)
    private String password;
    @NotBlank
    @Size(min = 3, max = 20)
    private String confirmPassword;
}
