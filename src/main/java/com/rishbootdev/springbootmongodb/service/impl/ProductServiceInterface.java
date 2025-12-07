package com.rishbootdev.springbootmongodb.service.impl;

import com.rishbootdev.springbootmongodb.entity.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product addProduct(Product product);
    Product getProductById(String id);
    void deleteProductById(String id);
    List<Product> getAll();
}
