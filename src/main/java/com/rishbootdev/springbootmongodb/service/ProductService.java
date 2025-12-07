package com.rishbootdev.springbootmongodb.service;

import com.rishbootdev.springbootmongodb.entity.Product;
import com.rishbootdev.springbootmongodb.repository.ProductRepository;
import com.rishbootdev.springbootmongodb.service.impl.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {

    private final ProductRepository repo;

    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    }

    @Override
    public void deleteProductById(String id) {
        Product product=repo.findById(id).orElseThrow(()-> new RuntimeException("the product with id not found"));

        repo.delete(product);
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }
}
