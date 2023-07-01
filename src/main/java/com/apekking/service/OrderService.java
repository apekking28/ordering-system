package com.apekking.service;

import com.apekking.entity.Customer;
import com.apekking.entity.Order;
import com.apekking.entity.Product;
import com.apekking.repository.CustomerRepository;
import com.apekking.repository.OrderRepository;
import com.apekking.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;


    private final CustomerRepository customerRepository;


    private final ProductRepository productRepository;

    public ResponseEntity<Order> createOrder(Order order) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        order.setOrderId(uuidString);
        order.setOrderDate();

        // Retrieve a Customer object based on customerId
        Optional<Customer> optionalCustomer = customerRepository.findById(order.getCustomer().getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Connects the Customer object with the Order object
            order.setCustomer(customer);
        }

        Optional<Product> optionalProduct = productRepository.findById(order.getProduct().getProductId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(order.getQuantity() <= product.getStock() && product.getStock() != 0){
                order.setProduct(product);
                order.setAmount(BigDecimal.valueOf((order.getQuantity() * Integer.valueOf(product.getProductPrice()))));
                product.setStock(product.getStock() - order.getQuantity());
                productRepository.save(product);
            }else {
                return null;
            }
        }
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
}
