package com.apekking.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private BigDecimal amount;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @CreationTimestamp
    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    public void setOrderDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.orderDate = Timestamp.valueOf(currentDateTime).toLocalDateTime();
    }
}
