package com.rishbootdev.springbootmongodb.entity;

import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
public class Order {

    @Id
    private String id;
    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;
}
