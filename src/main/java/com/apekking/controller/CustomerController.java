package com.apekking.controller;

import com.apekking.entity.Customer;
import com.apekking.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return customerServices.getAllCustomers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String customerId){
        return customerServices.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return customerServices.addCustomer(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable("id") String customerId, @RequestBody Customer customer){
        return customerServices.editCustomer(customerId,customer);
    }

}
