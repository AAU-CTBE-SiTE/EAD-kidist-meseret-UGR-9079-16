package com.craft.customer_service.controller;

import com.craft.customer_service.dto.OrderResponse;
import com.craft.customer_service.entity.Customer;
import com.craft.customer_service.repository.CustomerRepository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final RestClient restClient;

    public CustomerController(CustomerRepository customerRepository,
                              RestClient restClient) {
        this.customerRepository = customerRepository;
        this.restClient = restClient;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Save a new customer
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Get all orders belonging to a customer
    @GetMapping("/{id}/orders")
    public List<OrderResponse> getCustomerOrders(@PathVariable Long id) {

        return restClient.get()
                .uri("/orders/customer/{customerId}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderResponse>>() {});
    }
}
