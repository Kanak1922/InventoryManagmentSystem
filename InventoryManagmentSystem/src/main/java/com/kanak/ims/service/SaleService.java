package com.kanak.ims.service;

import com.kanak.ims.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SaleService {


    Double getTodayProfit();
    Double getTodayLoss();
    Double getCustomProfit(LocalDate startDate,LocalDate endDate);
    Double getCustomLoss(LocalDate startDate,LocalDate endDate);
    Double getYearlyProfit(int year);
    Double getYearlyLoss(int year);
    Double getTodayTotalLoss();
    Double getCustomTodayLoss(LocalDate startDate,LocalDate endDate);
    Double getYearlyTotalLoss(int year);

}
