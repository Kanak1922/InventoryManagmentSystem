package com.kanak.ims.service;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public interface InvoiceService {


    Integer getNoOfInvoiceToday();

    boolean addInvoice(InvoiceDTO invoice);

    List<ProductResponseDTO> getTodayInnvoiceDetails();

    List<ProductResponseDTO> getCustomInvoiceDetails(LocalDate startDate, LocalDate endDate);

    List<ProductResponseDTO> getYearlyInvoiceDetails(int year);



}
