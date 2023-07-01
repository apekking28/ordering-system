package com.apekking.service;

import com.apekking.entity.Product;
import com.apekking.exception.InvalidException;
import com.apekking.exception.NotFoundException;
import com.apekking.exception.NotNullOrEmptyException;
import com.apekking.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.apekking.service.utils.Utils.isNullOrEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public ResponseEntity<List<Product>> getProducts() throws Exception {
        try {
            List<Product> products = productRepository.findAll();
            log.info("Successfully get list products");
            return ResponseEntity.ok(products);
        } catch (Exception ex) {
            log.error("Failed to get products: " + ex.getMessage());
            throw new Exception("Failed to add new User. Please try again later.");
        }
    }

    public ResponseEntity<Product> addProduct(Product product) throws Exception {
        try {
            if (isNullOrEmpty(product.getProductPrice()) || isNullOrEmpty(product.getProductDescription())) {
                throw new NotNullOrEmptyException("All fields must be filled");
            }

            if (product.getProductPrice().compareTo(String.valueOf(BigDecimal.ZERO)) <= 0) {
                log.info("Product price must be greater than 0");
                throw new InvalidException("Product price must be greater than 0");
            }

            if (product.getStock() <= 0) {
                log.info("Stock must be greater than 0");
                throw new InvalidException("Stock must be greater than 0");
            }

            log.info("Successfully add new product");
            productRepository.save(product);
            return ResponseEntity.ok(product);
        } catch (NotNullOrEmptyException | InvalidException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to add products: " + ex.getMessage());
            throw new Exception("Failed to add new product. Please try again later.");
        }
    }


    public ResponseEntity<Product> editProduct(String productId, Product product) throws Exception {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                product.setProductId(productId);
                productRepository.save(product);
                return ResponseEntity.ok(product);
            } else {
                throw new NotFoundException("Product with ID " + productId + " not found");
            }
        } catch (NotFoundException ex) {
            log.error("Product not found: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to edit product: " + ex.getMessage());
            throw new Exception("Failed to edit product. Please try again later.");
        }
    }

    public ResponseEntity<Product> getProductById(String productId) throws Exception {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                return ResponseEntity.ok(product);
            } else {
                throw new NotFoundException("Product with ID " + productId + " not found");
            }
        } catch (NotFoundException ex) {
            log.error("Product not found: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to retrieve : " + ex.getMessage());
            throw new Exception("Failed to retrieve product. Please try again later.");
        }
    }
}
