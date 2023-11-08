package com.kanak.ims.controller;

import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import com.kanak.ims.repository.InvoiceRepository;
import com.kanak.ims.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    @RequestMapping(value = "/addInvoice",method = RequestMethod.POST)
    public void addInvoice(Invoice invoice){
        invoiceService.addInvoice(invoice);
    }

    @RequestMapping(value ="/todayInvoices",method = RequestMethod.GET)
    public List<Product> getDailyInnvoiceDetails(){
        return invoiceService.getTodayInnvoiceDetails();
    }
    @RequestMapping(value ="/todayProfit",method = RequestMethod.GET)
    public Long getTodayProfit(){
        return invoiceService.getTodayProfit();
    }

    @RequestMapping(value = "/customInvoices/{startDate}/{endDate}",method = RequestMethod.GET)
    public List<Product> getCustomInvoiceDetails(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        return invoiceService.getCustomInvoiceDetails(startDate,endDate);
    }

    @RequestMapping(value = "/customProfit/{startDate}/{endDate}",method = RequestMethod.GET)
    public Long getCustomProfit(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        return invoiceService.getCustomProfit(startDate,endDate);
    }
    @RequestMapping(value = "/yearlyInvoice/{year}",method = RequestMethod.GET)
    public List<Product> getYearlyInvoiceDetails(@PathVariable int year){
        return invoiceService.getYearlyInvoiceDetails(year);
    }

    @RequestMapping(value = "/yearlyProfit/{year}",method = RequestMethod.GET)
    public Long getYearlyProfit(@PathVariable int year){
        return invoiceService.getYearlyProfit(year);
    }

}
