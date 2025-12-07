package com.rishbootdev.springbootmongodb.service;


import com.rishbootdev.springbootmongodb.dto.OrderSearchCriteria;
import com.rishbootdev.springbootmongodb.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderSearchService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> searchOrders(OrderSearchCriteria searchCriteria) {
        Query query = new Query();

        if (searchCriteria.getStatus() != null) {
            query.addCriteria(Criteria.where("status")
                    .is(searchCriteria.getStatus()));
        }

        if (searchCriteria.getMinPrice() != null) {
            query.addCriteria(Criteria.where("totalPrice")
                    .gte(searchCriteria.getMinPrice()));
        }

        if (searchCriteria.getMaxPrice() != null) {
            query.addCriteria(Criteria.where("totalPrice")
                    .lte(searchCriteria.getMaxPrice()));
        }

        if (searchCriteria.getCity() != null) {
            query.addCriteria(Criteria.where("address.city")
                    .is(searchCriteria.getCity()));
        }

        if (searchCriteria.getDateFrom() != null) {
            query.addCriteria(Criteria.where("createdAt")
                    .gte(searchCriteria.getDateFrom()));
        }

        // Add sorting
        if (searchCriteria.getSortBy() != null) {
            Sort.Direction direction = searchCriteria.isAscending() ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            query.with(Sort.by(direction, searchCriteria.getSortBy()));
        }

        // Add pagination
        if (searchCriteria.getPage() != null && searchCriteria.getSize() != null) {
            Pageable pageable = PageRequest.of(
                    searchCriteria.getPage(),
                    searchCriteria.getSize()
            );
            query.with(pageable);
        }

        return mongoTemplate.find(query, Order.class);
    }
}

