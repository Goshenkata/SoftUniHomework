package com.example.xml.repository;

import com.example.xml.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> getAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal price, BigDecimal price2);
}
