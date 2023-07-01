package com.apekking.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String orderId;
    private String productPrice;
    private String productDescription;
    private int stock;

//    @OneToMany(mappedBy = "product")
//    private List<Order> orderList;

}
