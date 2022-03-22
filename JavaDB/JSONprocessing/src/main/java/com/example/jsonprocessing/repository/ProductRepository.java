package com.example.jsonprocessing.repository;

import com.example.jsonprocessing.DTO.Q1DTO;
import com.example.jsonprocessing.DTO.Q2DTO;
import com.example.jsonprocessing.DTO.Q2DTOProduct;
import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.entities.Product;
import com.example.jsonprocessing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new com.example.jsonprocessing.DTO.Q1DTO(p.name, p.price, concat(p.seller.firstName, ' ', p.seller.lastName)) from Product p where p.buyer is null and p.price between 500 and 1000 order by p.price")
    List<Q1DTO> shopQ1();

    @Query("select new com.example.jsonprocessing.DTO.Q2DTO(u.firstName, u.lastName) from User u where u.productsSold.size > 0 order by u.firstName, u.lastName")
    List<Q2DTO> shopQ2();

    @Query("select new com.example.jsonprocessing.DTO.Q2DTOProduct(p.name, p.price, p.buyer.firstName, p.buyer.lastName) from Product p where p.seller.firstName like ?1 and p.seller.lastName like ?2 and p.buyer is not null")
    List<Q2DTOProduct> shopQ2Product(String firstName, String lastName);

    @Query("select avg(p.price) from Product p where ?1 in (p.categories)")
    Double getAvgForCategory(Category category);
    @Query("select sum(p.price) from Product p")
    Double getTotalForCategory(Category category);
}
