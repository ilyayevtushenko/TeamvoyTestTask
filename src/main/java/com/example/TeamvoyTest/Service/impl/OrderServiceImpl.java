package com.example.TeamvoyTest.Service.impl;

import com.example.TeamvoyTest.Service.OrderService;
import com.example.TeamvoyTest.model.entity.Order;
import com.example.TeamvoyTest.model.repository.OrderRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setCreationTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (order.isValid()) {
            return order;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAll() {
       return orderRepository.findAll();
    }
}
