package com.example.spotifyplaylistapp.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

}
