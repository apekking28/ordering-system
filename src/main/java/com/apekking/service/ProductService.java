package com.apekking.service;

import com.apekking.entity.Product;
import com.apekking.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Product> addProduct(Product product) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        product.setProductId(uuidString);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> editProduct(String productId, Product product) {
        Optional<Product> product1 = productRepository.findById(productId);
        if (product1.isPresent()) {
            product.setProductId(productId);
            productRepository.save(product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Product> getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product product1 = product.get();
            return ResponseEntity.ok(product1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
