package com.example.TeamvoyTest;

import com.example.TeamvoyTest.model.domain.Order;
import com.example.TeamvoyTest.model.entity.OrderEntity;
import com.example.TeamvoyTest.model.repository.OrderRepository;
import com.example.TeamvoyTest.service.impl.OrderServiceImpl;
import com.example.TeamvoyTest.service.mapper.ItemMapper;
import com.example.TeamvoyTest.service.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final OrderMapper orderMapper = new OrderMapper(new ItemMapper());

    @BeforeEach
    public void setUp(){
        this.orderService = new OrderServiceImpl(orderRepository, orderMapper);
    }

    @Test
    void createOrderTest(){
        Order order = new Order();
        order.setId(1L);
        order.setCreationTime(LocalDateTime.now());
        order.setQuantity(2);
        order.setPrice(200.0);

        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);

        orderService.createOrder(order);
        verify(orderRepository).save(orderMapper.orderToOrderEntity(order));
    }

    @Test
    void createOrderTestWhenOrderIsNull(){
        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(null));
    }

    @Test
    void findByIdTest () {
        Order order = new Order();
        order.setId(1L);
        order.setCreationTime(LocalDateTime.now());
        order.setQuantity(2);
        order.setPrice(200.0);

        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(orderEntity));


        orderService.createOrder(order);
        Order foundOrder = orderService.findById(1L);
        assertEquals(order.getId(), foundOrder.getId());
    }

    @Test
    void findByIdWhenIsNotValid() {
        Order order = new Order();
        order.setId(1L);
        order.setCreationTime(LocalDateTime.of(2017, 2, 12, 10, 30));
        order.setQuantity(2);
        order.setPrice(200.0);
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(orderEntity));
        
        orderService.createOrder(order);
        assertThrows(IllegalArgumentException.class, () -> orderService.findById(1L));
    }

    @Test
    void deleteByIdTest() {
        Order order = new Order();
        order.setId(1L);
        order.setCreationTime(LocalDateTime.now());
        order.setQuantity(2);
        order.setPrice(200.0);
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);
        when(orderRepository.findById(order.getId())).thenThrow(IllegalArgumentException.class);
        orderService.createOrder(order);
        orderService.deleteById(order.getId());
        assertThrows(IllegalArgumentException.class, () -> orderService.findById(order.getId()));

    }

    @Test
    void findAllTest() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setCreationTime(LocalDateTime.now());
        order1.setQuantity(2);
        order1.setPrice(200.0);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setCreationTime(LocalDateTime.now());
        order2.setQuantity(1);
        order2.setPrice(255.0);

        OrderEntity orderEntity1 = orderMapper.orderToOrderEntity(order1);
        OrderEntity orderEntity2 = orderMapper.orderToOrderEntity(order2);

        Set<OrderEntity> sortedSet = new HashSet<>();
        sortedSet.add(orderEntity1);
        sortedSet.add(orderEntity2);
        Iterable<OrderEntity> iterable = sortedSet;


        when(orderRepository.save(eq(orderEntity1))).thenReturn(orderEntity1);
        when(orderRepository.save(eq(orderEntity2))).thenReturn(orderEntity2);
        when(orderRepository.findAll()).thenReturn(iterable);

        orderService.createOrder(order1);
        orderService.createOrder(order2);

        orderService.findAll();
        verify(orderRepository).findAll();
    }
}
