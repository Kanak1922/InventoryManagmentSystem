package com.kanak.ims.controller;


import com.kanak.ims.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

///api/admin/...
///api/user/...
@RestController
@RequestMapping("/api")
public class SaleController {

    @Autowired
    private SaleServiceImpl saleService;

    @RequestMapping(value = "/user/sales/todayProfit", method = RequestMethod.GET)
    public Double getTodayProfit() {
        return saleService.getTodayProfit();
    }

    @RequestMapping(value = "/user/sales/todayLoss", method = RequestMethod.GET)
    public Double getTodayLoss() {
        return saleService.getTodayLoss();
    }

    @RequestMapping(value = "/user/sales/todayTotalLoss", method = RequestMethod.GET)
    public Double getTodayTotalLoss() {
        return saleService.getTodayTotalLoss();
    }

    @RequestMapping(value = "/admin/sales/customProfit", method = RequestMethod.GET)
    public Double getCustomProfit(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return saleService.getCustomProfit(startDate, endDate);
    }

    @RequestMapping(value = "/admin/sales/customLoss", method = RequestMethod.GET)
    public Double getCustomLoss(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return saleService.getCustomLoss(startDate, endDate);
    }


    @RequestMapping(value = "/admin/sales/customTotalLoss", method = RequestMethod.GET)
    public Double getCustomTotalLoss(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return saleService.getCustomTodayLoss(startDate, endDate);

    }

    @RequestMapping(value = "/admin/sales/yearlyProfit", method = RequestMethod.GET)
    public Double getYearlyProfit(@RequestParam("year") int year) {
        return saleService.getYearlyProfit(year);
    }

    @RequestMapping(value = "/admin/sales/yearlyLoss", method = RequestMethod.GET)
    public Double getYearlyLoss(@RequestParam("year") int year) {
        return saleService.getYearlyLoss(year);
    }


    @RequestMapping(value = "/admin/sales/yearlyTotalLoss", method = RequestMethod.GET)
    public Double getYearlyTotalLoss(@RequestParam("year") int year) {
        return saleService.getYearlyTotalLoss(year);
    }

}
