package com.apekking.controller;

import com.apekking.entity.Product;
import com.apekking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> editProduct(@PathVariable("id")String productId,@RequestBody Product product){
        return productService.editProduct(productId,product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")String productId){
        return productService.getProductById(productId);
    }

}
