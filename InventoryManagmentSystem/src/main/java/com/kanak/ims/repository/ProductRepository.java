package com.kanak.ims.repository;

import com.kanak.ims.model.Category;
import com.kanak.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product p WHERE p.expiry_Date < ?#{T(java.time.LocalDate).now().plusDays(30)}",nativeQuery = true)
    List<Product> findProductsNearExpiry();
    @Query(value = "select * from product where quantity < :val",nativeQuery = true)
    List<Product> findProductsLowInStock(@Param("val") Integer val);

    @Query(value = "select * from product where batch_no = :bno",nativeQuery = true)
    List<Product> findProductsByBatchNo(@Param("bno") String batchNo);

    @Query(value = "select * from product where category_id = :id",nativeQuery = true)
    List<Product> findByCategoryType(@Param("id") int id);
}
