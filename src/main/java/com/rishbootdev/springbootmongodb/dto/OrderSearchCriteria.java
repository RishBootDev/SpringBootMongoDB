package com.rishbootdev.springbootmongodb.dto;

import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearchCriteria {

    private OrderStatus status;

    private Double minPrice;
    private Double maxPrice;

    private String city;

    private LocalDateTime dateFrom;

    private String sortBy;
    private boolean ascending = true;

    // Pagination fields
    private Integer page;
    private Integer size;
}
