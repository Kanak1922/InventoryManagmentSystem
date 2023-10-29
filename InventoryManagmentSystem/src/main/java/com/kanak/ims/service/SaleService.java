package com.kanak.ims.service;

import com.kanak.ims.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {
    List<Product> getTodaySaleDetails();
    Long getTodayProfit();
    List<Product> getCustomSaleDetails(LocalDate startDate,LocalDate endDate);
    Long getCustomProfit(LocalDate startDate,LocalDate endDate);
    List<Product> getYearlySaleDetails(int year);

    Long getYearlyProfit(int year);
}
