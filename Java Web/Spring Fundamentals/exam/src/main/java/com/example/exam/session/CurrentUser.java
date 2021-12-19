package com.example.exam.session;

import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class CurrentUser {
    private Long id;

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public CurrentUser(Long id) {
        this.id = id;
    }

}
