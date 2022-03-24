package com.example.xml.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Q1ProductsDTO implements Serializable {
    @XmlElement(name = "product")
    List<Qd1ProductDTO> productDTOs;

    public Q1ProductsDTO() {
    }

    public List<Qd1ProductDTO> getProductDTOs() {
        return productDTOs;
    }

    public Q1ProductsDTO setProductDTOs(List<Qd1ProductDTO> productDTOs) {
        this.productDTOs = productDTOs;
        return this;
    }
}
