package com.kanak.ims.service;

import com.kanak.ims.model.Product;
import com.kanak.ims.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SalesRepository salesRepository;
    @Override
    public List<Product> getTodaySaleDetails() {
        return salesRepository.getDailySaleDetails();
    }

    @Override
    public Long getTodayProfit() {
        return salesRepository.getTodayProfit();
    }

    @Override
    public List<Product> getCustomSaleDetails(LocalDate startDate, LocalDate endDate) {
        return salesRepository.getCustomSaleDetails(startDate,endDate);
    }

    @Override
    public Long getCustomProfit(LocalDate startDate, LocalDate endDate) {
        return salesRepository.getCustomProfit(startDate,endDate);
    }

    @Override
    public List<Product> getYearlySaleDetails(int year) {
        return salesRepository.getYearlySaleDetails(year);
    }

    @Override
    public Long getYearlyProfit(int year) {
        return salesRepository.getYearlyProfit(year);
    }
}
