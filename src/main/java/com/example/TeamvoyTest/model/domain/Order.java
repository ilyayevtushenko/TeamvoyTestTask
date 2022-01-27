package com.example.TeamvoyTest.model.domain;


import com.example.TeamvoyTest.model.entity.ItemEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Double price;
    private Integer quantity;
    private Item item;

    private LocalDateTime creationTime;
}
