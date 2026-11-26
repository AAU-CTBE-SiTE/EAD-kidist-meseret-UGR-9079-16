package com.craft.orderservice.controller;

import com.craft.orderservice.entity.Order;
import com.craft.orderservice.repository.OrderRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Save a new order
    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // Get an order by its ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Get all orders belonging to a customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(
            @PathVariable Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
