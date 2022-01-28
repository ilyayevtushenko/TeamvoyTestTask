package com.example.TeamvoyTest.controller;

import com.example.TeamvoyTest.model.domain.Item;
import com.example.TeamvoyTest.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Set<Item> findAll() {
        return itemService.findAll();
    }

    @DeleteMapping
    public Item takeCheapestItem() {
        Item cheapestItemEntity = itemService.findCheapestItem();
        itemService.deleteItem(cheapestItemEntity);
        return cheapestItemEntity;
    }
}
