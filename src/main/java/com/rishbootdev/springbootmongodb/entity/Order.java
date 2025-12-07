package com.rishbootdev.springbootmongodb.entity;

import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "orders")
@Data
@CompoundIndex(name="idx_quantity_status",def="{ 'quantity':-1, 'status':1 }")
public class Order {

    @Id
    private String id;

    private String name;
    private Integer quantity;
    private Double totalPrice;
    @Indexed
    private OrderStatus status;

    @CreatedDate
    @Indexed
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Indexed
    private LocalDateTime updatedAt;
}
