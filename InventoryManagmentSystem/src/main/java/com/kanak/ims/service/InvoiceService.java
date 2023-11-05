package com.kanak.ims.service;

import com.kanak.ims.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    List<Product> getTodayInnvoiceDetails();
    Long getTodayProfit();
    List<Product> getCustomInvoiceDetails(LocalDate startDate,LocalDate endDate);
    Long getCustomProfit(LocalDate startDate,LocalDate endDate);
    List<Product> getYearlyInvoiceDetails(int year);

    Long getYearlyProfit(int year);
}
