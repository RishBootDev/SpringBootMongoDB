package com.rishbootdev.springbootmongodb.dto;

import com.rishbootdev.springbootmongodb.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {

    private Order order;
    private List<String> ids;
}
