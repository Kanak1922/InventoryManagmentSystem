package com.kanak.ims.service;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public interface InvoiceService {


    void addInvoice(InvoiceDTO invoice);
    List<Product> getTodayInnvoiceDetails();
    Long getTodayProfit();
    List<Product> getCustomInvoiceDetails(LocalDate startDate,LocalDate endDate);
    Long getCustomProfit(LocalDate startDate,LocalDate endDate);
    List<Product> getYearlyInvoiceDetails(int year);

    Long getYearlyProfit(int year);
}
