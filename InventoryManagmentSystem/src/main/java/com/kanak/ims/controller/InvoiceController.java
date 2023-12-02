package com.kanak.ims.controller;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    @PostMapping("/addInvoice")
    public void addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        invoiceService.addInvoice(invoiceDTO);
    }

    @RequestMapping(value = "/todayInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getDailyInnvoiceDetails() {
        return ResponseEntity.ok(invoiceService.getTodayInnvoiceDetails());
    }

    @RequestMapping(value = "/todayProfit", method = RequestMethod.GET)
    public Long getTodayProfit() {
        return invoiceService.getTodayProfit();
    }

    @RequestMapping(value = "/todayLoss", method = RequestMethod.GET)
    public Long getTodayLoss() {
        return invoiceService.getTodayLoss();
    }

    @RequestMapping(value = "/customInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomInvoiceDetails(@RequestParam("startDate") LocalDate sDate, @RequestParam("endDate") LocalDate eDate) {
        return ResponseEntity.ok(invoiceService.getCustomInvoiceDetails(sDate, eDate));
    }

    @RequestMapping(value = "/customProfit", method = RequestMethod.GET)
    public Long getCustomProfit(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return invoiceService.getCustomProfit(startDate, endDate);
    }

    @RequestMapping(value = "/customLoss", method = RequestMethod.GET)
    public Long getCustomLoss(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return invoiceService.getCustomLoss(startDate, endDate);
    }

    @RequestMapping(value = "/yearlyInvoice", method = RequestMethod.GET)
    public ResponseEntity<?> getYearlyInvoiceDetails(@RequestParam("year") int year) {
        return ResponseEntity.ok(invoiceService.getYearlyInvoiceDetails(year));
    }

    @RequestMapping(value = "/yearlyProfit", method = RequestMethod.GET)
    public Long getYearlyProfit(@RequestParam("year") int year) {
        return invoiceService.getYearlyProfit(year);
    }

    @RequestMapping(value = "/yearlyLoss", method = RequestMethod.GET)
    public Long getYearlyLoss(@RequestParam("year") int year) {
        return invoiceService.getYearlyLoss(year);
    }

}
