package com.rishbootdev.springbootmongodb.controllers;

import com.rishbootdev.springbootmongodb.entity.Order;
import com.rishbootdev.springbootmongodb.entity.enums.OrderStatus;
import com.rishbootdev.springbootmongodb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order saved = service.create(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = service.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        try {
            Order order = service.getById(id);
            return ResponseEntity.ok(order);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order updated) {
        try {
            Order order = service.update(updated, id);
            return ResponseEntity.ok(order);
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> findByStatus(@PathVariable OrderStatus status){
        return ResponseEntity.ok(service.findByStatus(status));
    }
    @GetMapping("findByTotalPriceGreaterThan/{price}")
    public ResponseEntity<List<Order>> findByTotalPriceGreaterThan(@PathVariable Double price){
        return ResponseEntity.ok(service.findByTotalPriceGreaterThan(price));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Order>> findByStatusAndQuantityLessThan(
            @RequestParam OrderStatus status,
            @RequestParam int quantity) {

        return ResponseEntity.ok(service.findByStatusAndQuantityLessThan(status, quantity));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Order>> findFirstByStatusOrderByCreatedAtDesc(
            @RequestParam OrderStatus status) {

        return ResponseEntity.ok(service.findFirstByStatusOrderByCreatedAtDesc(status));
    }
}
