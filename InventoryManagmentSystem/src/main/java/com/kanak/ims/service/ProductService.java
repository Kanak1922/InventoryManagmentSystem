package com.kanak.ims.service;

import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import com.kanak.ims.repository.ProductRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);

    List<Product> findProductByName(String name);

    void addProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> findNearExpiryProducts();
    List<Product> fetchLowStockProducts(int quantity);
    List<Product> findProductsByBatchNo(String batchNo);
    List<Product> findByCategoryType(Long id);

    void deleteByCategoryId(Long id);

}

