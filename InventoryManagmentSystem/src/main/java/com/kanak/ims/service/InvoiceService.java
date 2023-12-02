package com.kanak.ims.service;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.dto.ProductResponseDTO;
import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import com.kanak.ims.model.ProductDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service

public interface InvoiceService {


    void addInvoice(InvoiceDTO invoice);
    List<ProductResponseDTO> getTodayInnvoiceDetails();
    Long getTodayProfit();
    Long getTodayLoss();
    List<ProductResponseDTO> getCustomInvoiceDetails(LocalDate startDate,LocalDate endDate);
    Long getCustomProfit(LocalDate startDate,LocalDate endDate);
    Long getCustomLoss(LocalDate startDate,LocalDate endDate);
    List<ProductResponseDTO> getYearlyInvoiceDetails(int year);
    Long getYearlyProfit(int year);
    Long getYearlyLoss(int year);


}
