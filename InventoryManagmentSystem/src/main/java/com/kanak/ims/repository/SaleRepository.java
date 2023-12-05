package com.kanak.ims.repository;

import com.kanak.ims.model.Product;
import com.kanak.ims.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale,Double> {



    @Query(value = "Select SUM(profit) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id where i.invoice_date= ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    Double getTodayProfit();

    @Query(value = "Select SUM(loss) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id where i.invoice_date= ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    Double getTodayLoss();



    @Query(value = "Select SUM(profit) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id " +
            "where i.invoice_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    Double getCustomProfit(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);

    @Query(value = "Select SUM(loss) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id " +
            "where i.invoice_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    Double getCustomLoss(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);

    
    @Query(value = "Select SUM(profit) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id where YEAR(i.invoice_date) = :year",nativeQuery = true)
    Double getYearlyProfit(@Param("year") int year);

    @Query(value = "Select SUM(loss) from sale s join invoice i on s.invoice_invoice_id=i.invoice_id where YEAR(i.invoice_date) = :year",nativeQuery = true)
    Double getYearlyLoss(@Param("year") int year);

}
