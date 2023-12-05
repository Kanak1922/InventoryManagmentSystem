package com.kanak.ims.service;


import com.kanak.ims.model.Product;
import com.kanak.ims.repository.ProductRepository;
import com.kanak.ims.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Double getTodayProfit() {
        return saleRepository.getTodayProfit()!=null?saleRepository.getTodayProfit():0d;
    }

    @Override
    public Double getTodayLoss() {
        return saleRepository.getTodayLoss()!=null?saleRepository.getTodayLoss():0d;
    }

    @Override
    public Double getCustomProfit(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getCustomProfit(startDate,endDate)!=null?saleRepository.getCustomProfit(startDate,endDate):0d;
    }

    @Override
    public Double getCustomLoss(LocalDate startDate, LocalDate endDate) {
        return saleRepository.getCustomLoss(startDate,endDate)!=null?saleRepository.getCustomLoss(startDate,endDate):0d;
    }

    @Override
    public Double getYearlyProfit(int year) {
        return saleRepository.getYearlyProfit(year)!=null?saleRepository.getYearlyProfit(year):0d;
    }

    @Override
    public Double getYearlyLoss(int year) {
        return saleRepository.getYearlyLoss(year)!=null?saleRepository.getYearlyLoss(year):0d;
    }

    @Override
    public Double getTodayTotalLoss() {
        List<Product> productList=productRepository.productsExpiredToday();
        Double res=0d;
        for(Product product:productList){
            res+=(product.getPurchasePrice()*product.getQuantity());
        }
        res+=saleRepository.getTodayLoss()!=null?saleRepository.getTodayLoss():0d;
        return res!=null?res:0d;
    }

    @Override
    public Double getCustomTodayLoss(LocalDate startDate, LocalDate endDate) {
        List<Product> productList=productRepository.productsExpiredCustom(startDate,endDate);
        Double res=0d;
        for(Product product:productList){
            res+=(product.getPurchasePrice()*product.getQuantity());
        }
        res+=saleRepository.getCustomLoss(startDate,endDate)!=null?saleRepository.getCustomLoss(startDate,endDate):0d;
        return res!=null?res:0d;
    }

    @Override
    public Double getYearlyTotalLoss(int year) {
        List<Product> productList=productRepository.productsExpiredYearly(year);
        Double res=0d;
        for(Product product:productList){
            res+=(product.getPurchasePrice()*product.getQuantity());
        }
        res+=saleRepository.getYearlyLoss(year)!=null?saleRepository.getYearlyLoss(year):0d;
        return res!=null?res:0d;
    }
}
