package com.example.TeamvoyTest.Service;

import com.example.TeamvoyTest.model.entity.Order;


public interface OrderService {

    Order createOrder (Order order);
    Order findById(Long id);
    void deleteById (Long id);
    Iterable<Order> findAll();

}
