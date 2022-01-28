package com.example.TeamvoyTest.service.impl;

import com.example.TeamvoyTest.model.domain.Order;
import com.example.TeamvoyTest.model.entity.OrderEntity;
import com.example.TeamvoyTest.service.OrderService;
import com.example.TeamvoyTest.model.repository.OrderRepository;

import com.example.TeamvoyTest.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        if (order == null) throw new IllegalArgumentException();
        order.setCreationTime(LocalDateTime.now());
        orderRepository.save(orderMapper.orderToOrderEntity(order));
    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        if (orderEntity.isValid()) {
            return orderMapper.orderEntityToOrder(orderEntity);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Set<Order> findAll() {
        Iterable<OrderEntity> iterable = orderRepository.findAll();
        List<OrderEntity> orderEntities = new ArrayList<>();
        iterable.forEach(orderEntities::add);
        return orderEntities.stream().map(orderMapper::orderEntityToOrder).collect(Collectors.toSet());
    }
}
