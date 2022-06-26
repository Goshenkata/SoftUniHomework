package com.example.exam6.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Data
@NoArgsConstructor
public class CurrentUser {
    private String username;
    private boolean isLoggedIn;
    public void clear() {
        username = null;
        isLoggedIn = false;
    }
}
