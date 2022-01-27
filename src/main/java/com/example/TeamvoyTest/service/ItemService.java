package com.example.TeamvoyTest.service;

import com.example.TeamvoyTest.model.domain.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> findAll();
    Item findCheapestItem();
    void deleteItem(Item item);
}
