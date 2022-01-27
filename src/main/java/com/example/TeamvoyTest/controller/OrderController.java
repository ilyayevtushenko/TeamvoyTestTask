package com.example.TeamvoyTest.controller;


import com.example.TeamvoyTest.model.domain.Order;
import com.example.TeamvoyTest.model.entity.OrderEntity;
import com.example.TeamvoyTest.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

        private final OrderService orderService;

        @GetMapping
        public Iterable findAll() {
            return orderService.findAll();
        }


        @GetMapping("/{id}")
        public Order findOne(@PathVariable Long id){
            return orderService.findById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void create(@RequestBody Order order) {
            orderService.createOrder(order);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            orderService.findById(id);
            orderService.deleteById(id);
        }

        @PutMapping("/{id}")
        public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
            if (order.getId() != id) {
                throw new RuntimeException();
            }
            orderService.findById(id);
            orderService.createOrder(order);
        }
    }
