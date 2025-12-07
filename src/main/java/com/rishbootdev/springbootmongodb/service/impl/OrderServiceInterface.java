package com.rishbootdev.springbootmongodb.service.impl;

import com.rishbootdev.springbootmongodb.entity.Order;

import java.util.List;

public interface OrderServiceInterface {

    Order create(Order order);
    Order getById(String id);
    List<Order> getAll();
    void delete(String id);
    Order update(Order order,String id);
}
