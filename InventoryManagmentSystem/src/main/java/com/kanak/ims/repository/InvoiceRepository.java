package com.kanak.ims.repository;

import com.kanak.ims.model.Product;
import com.kanak.ims.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>{


    public Set<Invoice> findByInnvoiceDate(LocalDate date);

    @Query(value = "Select count(invoice_id) from invoice where invoice_date= ?#{T(java.time.LocalDate).now()}",nativeQuery = true)
    Integer getNoOfInvoicesToday();

    @Query(value = "select * from invoice where invoice_date between :startDate AND :endDate order by invoice_id",nativeQuery = true)
    public Set<Invoice> findByInnvoiceCustomDate(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

    @Query(value = "select * from invoice where YEAR(invoice_date) = :year order by invoice_id",nativeQuery = true)
    public Set<Invoice> findByInnvoiceYear(@Param("year")int year);

    @Query(value = "Select * from products p where p.id in " +
            "(select s.pid from sale s where s.invoice_date = ?#{T(java.time.LocalDate).now())} )"
            ,nativeQuery = true)
    List<Product> getDailySaleDetails();

    @Query(value = "Select * from products p where p.id in " +
            "(select s.pid from sale s where s.invoice_date BETWEEN :startDate AND :endDate)"
            ,nativeQuery = true)
    List<Product> getCustomSaleDetails(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "Select * from products p where p.id in " +
            "(select s.pid from sale s where YEAR(s.invoice_date) = :year )"
            ,nativeQuery = true)
    List<Product> getYearlySaleDetails(@Param("year") int year);


}
