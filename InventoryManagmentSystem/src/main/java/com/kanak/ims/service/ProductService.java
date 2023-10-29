package com.kanak.ims.service;

import com.kanak.ims.model.Product;
import com.kanak.ims.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> findNearExpiryProducts(LocalDate date);
    List<Product> fetchLowStockProducts(int quantity);
}

