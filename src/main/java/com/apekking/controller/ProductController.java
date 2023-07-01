package com.apekking.controller;

import com.apekking.entity.Product;
import com.apekking.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws Exception {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {
        return productService.addProduct(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> editProduct(@PathVariable("id")String productId,@RequestBody Product product) throws Exception {
        return productService.editProduct(productId,product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")String productId) throws Exception {
        return productService.getProductById(productId);
    }

}
