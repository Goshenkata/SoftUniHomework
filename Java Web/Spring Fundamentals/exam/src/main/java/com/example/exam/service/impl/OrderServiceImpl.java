package com.example.exam.service.impl;

import com.example.exam.model.DTO.binding.AddOrderBindingModel;
import com.example.exam.model.entity.CategoryEntity;
import com.example.exam.model.entity.OrderEntity;
import com.example.exam.repository.OrderRepository;
import com.example.exam.service.OrderService;
import com.example.exam.service.UserService;
import com.example.exam.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public OrderEntity add(AddOrderBindingModel addOrderBindingModel) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(addOrderBindingModel.getName());
        orderEntity.setPrice(addOrderBindingModel.getPrice());
        orderEntity.setDescription(addOrderBindingModel.getDescription());
        orderEntity.setOrderTime(LocalDateTime.now());
        CategoryEntity category = new CategoryEntity();
        category.setName(addOrderBindingModel.getCategory());
        category.setTimeNeeded();
        orderEntity.setCategory(category);
        System.out.println(orderEntity);

        System.out.println("######################################");
        System.out.println(currentUser.getId());
        System.out.println("######################################");
        orderEntity.setEmployee(userService.getById(currentUser.getId()));

        orderRepository.save(orderEntity);
        return orderEntity;
    }
}
