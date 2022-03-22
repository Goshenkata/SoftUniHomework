package com.example.jsonprocessing.DTO;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    @Expose
    String name;

    public CategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CategoryDTO setName(String name) {
        this.name = name;
        return this;
    }
}
