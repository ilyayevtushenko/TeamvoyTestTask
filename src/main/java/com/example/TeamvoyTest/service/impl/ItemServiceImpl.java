package com.example.TeamvoyTest.service.impl;

import com.example.TeamvoyTest.model.domain.Item;
import com.example.TeamvoyTest.model.entity.ItemEntity;
import com.example.TeamvoyTest.service.ItemService;
import com.example.TeamvoyTest.model.repository.ItemRepository;
import com.example.TeamvoyTest.service.mapper.ItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Set<Item> findAll() {
        Iterable<ItemEntity> iterable = itemRepository.findAll();
        List<ItemEntity> itemEntities = new ArrayList<>();
        iterable.forEach(itemEntities::add);
        return itemEntities.stream().map(itemMapper::itemEntityToItem).collect(Collectors.toSet());
    }

    @Override
    public Item findCheapestItem() {
       Iterable<ItemEntity> iterator = itemRepository.findAll();
       List<ItemEntity> itemEntities = new ArrayList<>();

       iterator.forEach(itemEntities::add);

       ItemEntity itemEntity = itemEntities.stream().min((Comparator.comparingDouble(ItemEntity::getPrice))).orElseThrow();

       return itemMapper.itemEntityToItem(itemEntity);

    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.deleteById(item.getId());
    }
}
