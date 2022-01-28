package com.example.TeamvoyTest;

import com.example.TeamvoyTest.model.domain.Item;
import com.example.TeamvoyTest.model.entity.ItemEntity;
import com.example.TeamvoyTest.model.repository.ItemRepository;
import com.example.TeamvoyTest.service.impl.ItemServiceImpl;
import com.example.TeamvoyTest.service.mapper.ItemMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ItemServiceImplTest {
    private ItemServiceImpl itemService;
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    private final ItemMapper itemMapper = new ItemMapper();

    @BeforeEach
    public void setUp(){
        this.itemService = new ItemServiceImpl(itemRepository, itemMapper);
    }

    @Test
    void findAllTest(){
        Item item1 = new Item();
        item1.setId(1L);
        item1.setPrice(255.0);
        item1.setName("Book 1: Game of Thrones");

        Item item2 = new Item();
        item2.setId(2L);
        item2.setPrice(455.0);
        item2.setName("Book 3: Storm of Swords");

        ItemEntity itemEntity1 = itemMapper.itemToItemEntity(item1);
        ItemEntity itemEntity2 = itemMapper.itemToItemEntity(item2);


        Set<ItemEntity> sortedSet = new HashSet<>();
        sortedSet.add(itemEntity1);
        sortedSet.add(itemEntity2);
        Iterable<ItemEntity> iterable = sortedSet;


        when(itemRepository.save(eq(itemEntity1))).thenReturn(itemEntity1);
        when(itemRepository.save(eq(itemEntity2))).thenReturn(itemEntity2);
        when(itemRepository.findAll()).thenReturn(iterable);

        itemRepository.save(itemEntity1);
        itemRepository.save(itemEntity2);

        itemService.findAll();
        verify(itemRepository).findAll();
    }

    @Test
    void findCheapestItemTest() {

        Item item1 = new Item();
        item1.setId(1L);
        item1.setPrice(200.0);
        item1.setName("Book 4: Feast for Crows");

        Item item2 = new Item();
        item2.setId(2L);
        item2.setPrice(475.0);
        item2.setName("Book 5: Dance with Dragons");

        ItemEntity itemEntity1 = itemMapper.itemToItemEntity(item1);
        ItemEntity itemEntity2 = itemMapper.itemToItemEntity(item2);


        Set<ItemEntity> sortedSet = new HashSet<>();
        sortedSet.add(itemEntity1);
        sortedSet.add(itemEntity2);
        Iterable<ItemEntity> iterable = sortedSet;


        when(itemRepository.save(eq(itemEntity1))).thenReturn(itemEntity1);
        when(itemRepository.save(eq(itemEntity2))).thenReturn(itemEntity2);
        when(itemRepository.findAll()).thenReturn(iterable);

        itemRepository.save(itemEntity1);
        itemRepository.save(itemEntity2);

        itemService.findCheapestItem();
        verify(itemRepository).findAll();
    }
}
