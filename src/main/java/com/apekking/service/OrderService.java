package com.apekking.service;

import com.apekking.entity.Customer;
import com.apekking.entity.Order;
import com.apekking.entity.Product;
import com.apekking.exception.InvalidException;
import com.apekking.exception.NotFoundException;
import com.apekking.repository.CustomerRepository;
import com.apekking.repository.OrderRepository;
import com.apekking.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {


    private final OrderRepository orderRepository;


    private final CustomerRepository customerRepository;


    private final ProductRepository productRepository;

    @Transactional
    public ResponseEntity<Order> createOrder(Order order) throws Exception {
        System.out.println(order);
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(order.getCustomer().getCustomerId());
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                order.setCustomer(customer);
            } else {
                log.info("Customer with ID " + order.getCustomer().getCustomerId() + " Not found");
                throw new NotFoundException("Customer with ID " + order.getCustomer().getCustomerId() + " Not found");
            }

            Optional<Product> optionalProduct = productRepository.findById(order.getProduct().getProductId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (order.getQuantity() <= product.getStock() && product.getStock() != 0) {
                    order.setProduct(product);
                    BigDecimal totalPrice = BigDecimal.valueOf(order.getQuantity()).multiply(new BigDecimal(product.getProductPrice()));
                    order.setAmount(totalPrice);
                    product.setStock(product.getStock() - order.getQuantity());
                    productRepository.save(product);
                } else {
                    log.info("Availability is not sufficient");
                    throw new InvalidException("Availability is not sufficient");
                }
            } else {
                log.info("Product with ID " + order.getCustomer().getCustomerId() + " Not found");
                throw new NotFoundException("Product with ID " + order.getProduct().getProductId() + " Not found");
            }

            log.info("Successfully add new order");
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } catch (NotFoundException | InvalidException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to add new order: " + ex.getMessage());
            throw new Exception("Failed to add new order. Please try again later.");
        }
    }


}
