package com.kanak.ims.service;

import com.kanak.ims.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getAllProductsActive();
    List<Product> getAllProductsInactive();
    Product getProductById(Long id);
    List<Product> findProductByName(String name);

    boolean addProduct(Product product);
    boolean updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> findNearExpiryProducts();

    List<Product> findExpiredProducts();
    List<Product> fetchLowStockProducts(int quantity);
    List<Product> findProductsByBatchNo(String batchNo);
    List<Product> findByCategoryType(Long id);

    void deleteByCategoryId(Long id);

    List<Product> productsExpiredToday();
    List<Product> productsExpiredCustom(LocalDate startDate, LocalDate endDate);
    List<Product> productsExpiredYearly(int year);
    List<Product> searchProduct(String s);

}

