package com.example.exam3.user;

import com.example.exam3.model.entity.User;
import com.example.exam3.repository.UserReporitory;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Data
public class CurrentUser {
    private String username;
    private final UserReporitory userReporitory;
    private boolean loggedIn;
    public void clear() {
        username = null;
        loggedIn = false;
    }

}
