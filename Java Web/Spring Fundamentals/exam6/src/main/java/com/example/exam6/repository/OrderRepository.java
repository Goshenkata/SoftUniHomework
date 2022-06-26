package com.example.exam6.repository;

import com.example.exam6.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByOrderByPriceDesc();
    @Query("select sum(e.category.neededTime) from OrderEntity e")
    Integer timeToPrepareOrders();
}
