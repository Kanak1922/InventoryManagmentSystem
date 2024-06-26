package com.kanak.ims.repository;

import com.kanak.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product p WHERE p.status='active' and p.expiry_date >= ?#{T(java.time.LocalDate).now()} and p.expiry_date < ?#{T(java.time.LocalDate).now().plusDays(30)}",nativeQuery = true)
    List<Product> findProductsNearExpiry();
    @Query(value = "select * from product where status='active' and quantity < :val",nativeQuery = true)
    List<Product> findProductsLowInStock(@Param("val") Integer val);
    @Query(value = "select * from product p WHERE p.status='active' and p.expiry_date < ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    List<Product> findExpiredProducts();
    @Query(value = "select * from product p where p.status='active' and batch_no = :bno",nativeQuery = true)
    List<Product> findProductsByBatchNo(@Param("bno") String batchNo);
   /* @Query(value = "select * from product where status='active' and category_id = :id",nativeQuery = true)
    List<Product> findByCategoryType(@Param("id") long id);*/
   @Query(value = "select * from product where status='active' and category_id = (select id from category " +
           "where category_name = :name)", nativeQuery = true)

   List<Product> findByCategoryType(@Param("name") String name);
    void deleteByCategoryId(long id);
    List<Product> findByName(String name);
    @Query(value = "delete from product where pid = :id",nativeQuery = true)
    void deleteProductById(@Param("id") long id);
    List<Product> findByStatus(String status);

    @Query(value = "select * from product p WHERE p.expiry_date = ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    List<Product> productsExpiredToday();

    @Query(value = "select * from product p WHERE p.expiry_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<Product> productsExpiredCustom(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select * from product p WHERE YEAR(p.expiry_date) = :year",nativeQuery = true)
    List<Product> productsExpiredYearly(@Param("year") int year);

    @Query(value= "select * from product where product_name like :s%",nativeQuery = true)
    List<Product> searchProduct(String s);


    boolean existsByNameAndBatchNo(String name, String batchNo);
}
