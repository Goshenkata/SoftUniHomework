package com.example.xml.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q4SoldProducts implements Serializable {
    @XmlAttribute(name = "count")
    Integer count;
    @XmlElement(name = "product")
    List<Q4Products> products;

    public Q4SoldProducts() {
    }

    public Integer getCount() {
        return count;
    }

    public Q4SoldProducts setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<Q4Products> getProducts() {
        return products;
    }

    public Q4SoldProducts setProducts(List<Q4Products> products) {
        this.products = products;
        return this;
    }
}
