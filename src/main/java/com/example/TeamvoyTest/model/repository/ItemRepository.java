package com.example.TeamvoyTest.model.repository;

import com.example.TeamvoyTest.model.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
}
