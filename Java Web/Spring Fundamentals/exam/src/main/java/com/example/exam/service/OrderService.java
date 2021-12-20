package com.example.exam.service;

import com.example.exam.model.DTO.binding.AddOrderBindingModel;
import com.example.exam.model.entity.OrderEntity;

public interface OrderService {
    OrderEntity add(AddOrderBindingModel addOrderBindingModel);
}
