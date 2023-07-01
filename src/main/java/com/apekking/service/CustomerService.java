package com.apekking.service;

import com.apekking.entity.Customer;
import com.apekking.exception.NotFoundException;
import com.apekking.exception.DuplicateException;
import com.apekking.exception.NotNullOrEmptyException;
import com.apekking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.apekking.service.utils.Utils.isNullOrEmpty;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;


    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        try {
            List<Customer> allCustomers = customerRepository.findAll();
            log.info("Successfully get list customer");
            return ResponseEntity.ok(allCustomers);
        } catch (Exception ex) {
            log.error("Failed to get users: " + ex.getMessage());
            throw new Exception("Failed to add new User. Please try again later.");
        }
    }

    public ResponseEntity<Customer> addCustomer(Customer customer) throws Exception {
        boolean duplicateUser = customerRepository.existsByCustomerName(customer.getCustomerName());
        try {
            if (isNullOrEmpty(customer.getCustomerName())
                    || isNullOrEmpty(customer.getAddress())
                    || customer.getPhone() == null) {
                log.error("all fields must be filled");
                throw new NotNullOrEmptyException("All fields must be filled");
            }

            if (duplicateUser) {
                log.error("User with name " + customer.getCustomerName() + " already exists");
                throw new DuplicateException("User with name " + customer.getCustomerName() + " already exists");
            }

            log.info("Successfully add new User");
            customerRepository.save(customer);

            return ResponseEntity.ok(customer);
        } catch (NotNullOrEmptyException | DuplicateException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to add new User: " + ex.getMessage());
            throw new Exception("Failed to add new User. Please try again later.");
        }
    }


    public ResponseEntity<Customer> editCustomer(String customerId, Customer customer) throws Exception {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                customer.setCustomerId(customerId);
                customerRepository.save(customer);
                return ResponseEntity.ok(customer);
            } else {
                throw new NotFoundException("Customer with ID " + customerId + " not found");
            }
        } catch (NotFoundException ex) {
            log.error("Customer not found: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to edit User: " + ex.getMessage());
            throw new Exception("Failed to edit User. Please try again later.");
        }
    }


    public ResponseEntity<Customer> getCustomerById(String customerId) throws Exception {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                return ResponseEntity.ok(customer);
            } else {
                throw new NotFoundException("Customer with ID " + customerId + " not found");
            }
        } catch (NotFoundException ex) {
            log.error("Customer not found: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to retrieve customer: " + ex.getMessage());
            throw new Exception("Failed to retrieve customer. Please try again later.");
        }
    }


}
