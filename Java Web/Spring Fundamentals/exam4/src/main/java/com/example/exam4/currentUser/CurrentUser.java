package com.example.exam4.currentUser;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class CurrentUser {
    private String username;
    private boolean loggedIn;

    public void clear() {
        username = null;
        loggedIn = false;
    }
}
