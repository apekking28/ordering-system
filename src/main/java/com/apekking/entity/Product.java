package com.apekking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String productId;

    private String productPrice;
    private String productDescription;
    private int stock;

//    @OneToMany(mappedBy = "product")
//    private List<Order> orderList;

    // Add this constructor
    public Product(String productId) {
        this.productId = productId;
    }
}
