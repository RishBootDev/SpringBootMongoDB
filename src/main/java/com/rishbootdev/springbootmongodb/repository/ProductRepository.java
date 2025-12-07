package com.rishbootdev.springbootmongodb.repository;

import com.rishbootdev.springbootmongodb.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
