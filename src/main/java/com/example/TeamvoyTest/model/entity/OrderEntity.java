package com.example.TeamvoyTest.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column (name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id")
    private ItemEntity itemEntity;

    private LocalDateTime creationTime;


    public boolean isValid() {
        LocalDateTime creationTime = this.creationTime;
        LocalDateTime currentDate = LocalDateTime.now();
        return (ChronoUnit.MINUTES.between(creationTime, currentDate) <= 10);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }


}