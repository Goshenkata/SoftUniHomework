package com.example.exam6.service;

import com.example.exam6.model.dto.AddOrderDTO;
import com.example.exam6.model.dto.OrderInfoDTO;
import com.example.exam6.model.entity.OrderEntity;
import com.example.exam6.repository.CategoryRepository;
import com.example.exam6.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class OrderService {
    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<OrderInfoDTO> getOrdersInfo() {
        return orderRepository
                .findAllByOrderByPriceDesc()
                .stream()
                .map(orderEntity -> {
                    OrderInfoDTO infoDTO = mapper.map(orderEntity, OrderInfoDTO.class);
                    infoDTO.setCat(orderEntity.getCategory().getName().name().toLowerCase(Locale.ROOT));
                    return infoDTO;
                }).toList();
    }

    public void add(AddOrderDTO addOrderDTO) {
        OrderEntity order = mapper.map(addOrderDTO, OrderEntity.class);
        order.setCategory(categoryRepository.findByName(addOrderDTO.getCategoryName()));
        order.setEmployee(userService.getCurrentUser());
        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Integer getTimeSum() {
        Integer timeToPrepareOrders = orderRepository.timeToPrepareOrders();
        return timeToPrepareOrders == null ? 0 : timeToPrepareOrders;
    }
}