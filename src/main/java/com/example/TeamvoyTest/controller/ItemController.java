package com.example.TeamvoyTest.controller;

import com.example.TeamvoyTest.Service.ItemService;
import com.example.TeamvoyTest.model.entity.Item;
import com.example.TeamvoyTest.model.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Iterable findAll() {
        return itemService.findAll();
    }

    @DeleteMapping
    public void deleteCheapestItem() {
        itemService.deleteItem(itemService.findCheapestItem());
    }
}
