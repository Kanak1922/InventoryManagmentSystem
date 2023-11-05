package com.kanak.ims.service;

import com.kanak.ims.model.Product;
import com.kanak.ims.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository innvoicesRepository;
    @Override
    public List<Product> getTodayInnvoiceDetails() {
        return innvoicesRepository.getDailySaleDetails();
    }

    @Override
    public Long getTodayProfit() {
        return innvoicesRepository.getTodayProfit();
    }

    @Override
    public List<Product> getCustomInvoiceDetails(LocalDate startDate, LocalDate endDate) {
        return innvoicesRepository.getCustomSaleDetails(startDate,endDate);
    }

    @Override
    public Long getCustomProfit(LocalDate startDate, LocalDate endDate) {
        return innvoicesRepository.getCustomProfit(startDate,endDate);
    }

    @Override
    public List<Product> getYearlyInvoiceDetails(int year) {
        return innvoicesRepository.getYearlySaleDetails(year);
    }

    @Override
    public Long getYearlyProfit(int year) {
        return innvoicesRepository.getYearlyProfit(year);
    }
}
