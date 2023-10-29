package com.kanak.ims.repository;

import com.kanak.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product",nativeQuery = true)
    List<Product> findProductsNearExpiry(LocalDate expiryDate);
    @Query(value = "select * from product",nativeQuery = true)
    List<Product> findProductsLowInStock(Integer quantity);
}
