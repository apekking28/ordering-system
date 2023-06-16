package com.apekking.service;

import com.apekking.entity.Customer;
import com.apekking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;


    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return ResponseEntity.ok(allCustomers);
    }

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        customer.setCustomerId(uuidString);
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> editCustomer(String customerId, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            customer.setCustomerId(customerId);
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Customer> getCustomerById(String customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
