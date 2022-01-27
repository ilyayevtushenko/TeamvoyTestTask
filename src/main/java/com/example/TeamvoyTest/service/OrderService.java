package com.example.TeamvoyTest.service;

import com.example.TeamvoyTest.model.domain.Order;

import java.util.Set;


public interface OrderService {

    void createOrder (Order order);
    Order findById(Long id);
    void deleteById (Long id);
    Set<Order> findAll();

}
