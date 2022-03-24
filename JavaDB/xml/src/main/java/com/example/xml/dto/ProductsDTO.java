package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDTO implements Serializable {
    @XmlElement(name = "product")
    List<ProductDTO> products;

    public ProductsDTO() {
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public ProductsDTO setProducts(List<ProductDTO> products) {
        this.products = products;
        return this;
    }
}
