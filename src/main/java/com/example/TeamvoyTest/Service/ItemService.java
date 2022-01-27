package com.example.TeamvoyTest.Service;

import com.example.TeamvoyTest.model.entity.Item;

public interface ItemService {
    Iterable<Item> findAll();
    Item findCheapestItem();
    void deleteItem(Item item);
}
