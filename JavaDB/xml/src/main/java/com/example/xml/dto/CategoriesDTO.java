package com.example.xml.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesDTO implements Serializable {
    @XmlElement(name = "category")
    List<CategoryDTO> categories;

    public CategoriesDTO() {
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public CategoriesDTO setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
        return this;
    }
}
