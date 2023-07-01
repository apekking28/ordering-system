package com.apekking.controller;

import com.apekking.entity.Customer;
import com.apekking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String customerId) throws Exception {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.addCustomer(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable("id") String customerId, @RequestBody Customer customer) throws Exception {
        return customerService.editCustomer(customerId,customer);
    }

}
