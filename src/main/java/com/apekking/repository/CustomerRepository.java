package com.apekking.repository;

import com.apekking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    boolean existsByCustomerName(String customerName);
}
