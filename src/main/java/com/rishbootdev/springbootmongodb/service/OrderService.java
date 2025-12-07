package com.rishbootdev.springbootmongodb.service;

import com.rishbootdev.springbootmongodb.entity.Order;
import com.rishbootdev.springbootmongodb.entity.Product;
import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import com.rishbootdev.springbootmongodb.repository.OrderRepository;
import com.rishbootdev.springbootmongodb.repository.ProductRepository;
import com.rishbootdev.springbootmongodb.service.impl.OrderServiceInterface;
import com.rishbootdev.springbootmongodb.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {

    private final OrderRepository repository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public Order update(Order order,String id) {
        Order existing = getById(id);

        existing.setQuantity(order.getQuantity());
        existing.setTotalPrice(order.getTotalPrice());
        existing.setStatus(order.getStatus());

        return repository.save(existing);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Order> findByTotalPriceGreaterThan(Double price) {
        return repository.findByTotalPriceGreaterThan(price);
    }

    @Override
    public List<Order> findByStatusAndQuantityLessThan(OrderStatus status, int quantity) {
        return repository.findByStatusAndQuantityLessThan(status,quantity);
    }

    @Override
    public List<Order> findFirstByStatusOrderByCreatedAtDesc(OrderStatus status) {
        return repository.findFirstByStatusOrderByCreatedAtDesc(status).stream().toList();
    }

    @Override
    public List<Order> findOrdersByStatusAndPrices(OrderStatus status, double minPrice) {
        return repository.findOrdersByStatusAndPrices(status,minPrice);
    }

    @Override
    public Order placeOrder(Order order, List<String> productIds) {

        List<Product> products=productIds.stream().map((productId)-> productService.getProductById(productId))
                        .toList();
        order.setStatus(OrderStatus.PROCESSING);
        order.setProducts(products);
        return repository.save(order);

    }
    @Override
    public void delete(String id) {
        getById(id);
        repository.deleteById(id);
    }
}
