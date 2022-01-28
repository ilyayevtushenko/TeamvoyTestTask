package com.example.TeamvoyTest.service.mapper;

import com.example.TeamvoyTest.model.domain.Item;
import com.example.TeamvoyTest.model.entity.ItemEntity;
import org.springframework.stereotype.Component;



@Component
public class ItemMapper {

    public ItemEntity itemToItemEntity (Item item) {
        if (item == null) return null;
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        return itemEntity;
    }

    public Item itemEntityToItem (ItemEntity itemEntity) {
        if (itemEntity == null) return null;
        Item item = new Item();
        item.setId(itemEntity.getId());
        item.setName(itemEntity.getName());
        item.setPrice(itemEntity.getPrice());
        return item;
    }
}
