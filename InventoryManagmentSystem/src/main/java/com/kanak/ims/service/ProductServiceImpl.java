package com.kanak.ims.service;

import com.kanak.ims.model.Product;
import com.kanak.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public void updateProduct(Long id, Product product) {
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findNearExpiryProducts(LocalDate date) {
        return productRepository.findProductsNearExpiry(date);
    }

    @Override
    public List<Product> fetchLowStockProducts(int quantity) {
        return productRepository.findProductsLowInStock(quantity);
    }


}

