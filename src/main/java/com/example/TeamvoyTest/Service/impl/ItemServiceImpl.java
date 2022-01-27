package com.example.TeamvoyTest.Service.impl;

import com.example.TeamvoyTest.Service.ItemService;
import com.example.TeamvoyTest.model.entity.Item;
import com.example.TeamvoyTest.model.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findCheapestItem() {
       Iterable<Item> iterator = itemRepository.findAll();
       List<Item> items = new ArrayList<>();

       iterator.forEach(items::add);

       return items.stream().min((Comparator.comparingDouble(Item::getPrice))).orElseThrow();

    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.deleteById(item.getId());
    }
}
