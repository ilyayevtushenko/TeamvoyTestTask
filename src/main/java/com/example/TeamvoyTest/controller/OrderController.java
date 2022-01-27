package com.example.TeamvoyTest.controller;


import com.example.TeamvoyTest.Service.OrderService;
import com.example.TeamvoyTest.model.entity.Order;
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
        public Order create(@RequestBody Order order) {
            return orderService.createOrder(order);

        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            orderService.findById(id);
            orderService.deleteById(id);
        }

        @PutMapping("/{id}")
        public Order updateBook(@RequestBody Order order, @PathVariable Long id) {
            if (order.getId() != id) {
                throw new RuntimeException();
            }
            orderService.findById(id);
            return orderService.createOrder(order);
        }
    }
