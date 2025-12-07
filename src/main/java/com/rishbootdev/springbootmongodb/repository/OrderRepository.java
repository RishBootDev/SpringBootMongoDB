package com.rishbootdev.springbootmongodb.repository;

import com.rishbootdev.springbootmongodb.entity.Order;
import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByStatus(OrderStatus status);
    List<Order> findByTotalPriceGreaterThan(Double price);
    List<Order> findByStatusAndQuantityLessThan(OrderStatus status,int quantity);
    Optional<Order> findFirstByStatusOrderByCreatedAtDesc(OrderStatus status);
}
