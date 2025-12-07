package com.rishbootdev.springbootmongodb.service.impl;

import com.rishbootdev.springbootmongodb.entity.Order;
import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {

    Order create(Order order);
    Order getById(String id);
    List<Order> getAll();
    void delete(String id);
    Order update(Order order,String id);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByTotalPriceGreaterThan(Double price);
    List<Order> findByStatusAndQuantityLessThan(OrderStatus status,int quantity);
    List<Order> findFirstByStatusOrderByCreatedAtDesc(OrderStatus status);
}
