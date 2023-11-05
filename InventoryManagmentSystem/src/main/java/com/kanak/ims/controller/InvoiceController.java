package com.kanak.ims.controller;

import com.kanak.ims.model.Product;
import com.kanak.ims.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @RequestMapping(value ="/todaySales",method = RequestMethod.GET)
    public List<Product> getDailyInnvoiceDetails(){
        return invoiceRepository.getDailySaleDetails();
    }
    @RequestMapping(value ="/todayProfit",method = RequestMethod.GET)
    public Long getTodayProfit(){
        return invoiceRepository.getTodayProfit();
    }
    @RequestMapping(value = "/customSales",method = RequestMethod.GET)
    public List<Product> getCustomInvoiceDetails(LocalDate startDate, LocalDate endDate){
        return invoiceRepository.getCustomSaleDetails(startDate,endDate);
    }

    @RequestMapping(value = "/customProfit",method = RequestMethod.GET)
    public Long getCustomProfit(LocalDate startDate,LocalDate endDate){
        return invoiceRepository.getCustomProfit(startDate,endDate);
    }
    @RequestMapping(value = "/yearlySales",method = RequestMethod.GET)
    public List<Product> getYearlyInvoiceDetails(int year){
        return invoiceRepository.getYearlySaleDetails(year);
    }
    @RequestMapping(value = "/yearlyProfit",method = RequestMethod.GET)
    public Long getYearlyProfit(int year){
        return invoiceRepository.getYearlyProfit(year);
    }

}
