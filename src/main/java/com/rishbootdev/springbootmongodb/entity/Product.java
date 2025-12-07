package com.rishbootdev.springbootmongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;

    @Indexed
    private String category;

    @Indexed
    private double price;
}
