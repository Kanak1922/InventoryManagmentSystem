package com.kanak.ims.repository;

import com.kanak.ims.model.Product;
import com.kanak.ims.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>{

    @Query(value = "Select * from products p where p.id in (select s.pid from sale s where s.innvoice_date = ?#{T(java.time.LocalDate).now())} )",nativeQuery = true)
    List<Product> getDailySaleDetails();

    @Query(value = "Select SUM(profit) from sales s join invoice i on s.invoice_id=i.invoice_id = ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    Long getTodayProfit();

    @Query(value = "Select * from products p where p.id in (select s.productId from sale s where s.innvoiceDate BETWEEN :startDate AND :endDate )",nativeQuery = true)
    List<Product> getCustomSaleDetails(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);

    @Query(value = "select SUM(s.profit) from sale s where s.innvoiceDate BETWEEN :startDate AND :endDate",nativeQuery = true)
    Long getCustomProfit(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);
    @Query(value = "Select * from products p where p.id in (select s.productId from sale s WHERE YEAR(s.innvoiceDate) = :year)",nativeQuery = true)
    List<Product> getYearlySaleDetails(@Param("year") int year);

    @Query(value = "select SUM(s.profit) from sale s WHERE YEAR(s.innvoiceDate) = :year",nativeQuery = true)
    Long getYearlyProfit(@Param("year") int year);
}
