package com.apekking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String address;
    private Long phone;

//    @OneToMany(mappedBy = "customer")
//    private List<Order> orderList;

    // Add this constructor

    public Customer(String customerId) {
        this.customerId = customerId;
    }
}
