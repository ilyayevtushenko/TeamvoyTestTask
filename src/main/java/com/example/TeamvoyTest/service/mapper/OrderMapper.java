package com.example.TeamvoyTest.service.mapper;

import com.example.TeamvoyTest.model.domain.Order;
import com.example.TeamvoyTest.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper {

    private final ItemMapper itemMapper;

    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrderEntity orderToOrderEntity (Order order) {
        if (order == null) return null;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setPrice(order.getPrice());
        orderEntity.setQuantity(order.getQuantity());
        orderEntity.setItemEntity(itemMapper.itemToItemEntity(order.getItem()));
        orderEntity.setCreationTime(order.getCreationTime());
        return orderEntity;
    }

    public Order orderEntityToOrder (OrderEntity orderEntity) {
        if (orderEntity == null) return null;
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setPrice(orderEntity.getPrice());
        order.setQuantity(orderEntity.getQuantity());
        order.setItem(itemMapper.itemEntityToItem(orderEntity.getItemEntity()));
        order.setCreationTime(orderEntity.getCreationTime());
        return order;
    }
}
