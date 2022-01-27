package com.example.TeamvoyTest.model.repository;

import com.example.TeamvoyTest.model.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
