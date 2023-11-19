package com.kanak.ims.controller;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import com.kanak.ims.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    @PostMapping("/addInvoice")
    public void addInvoice(@RequestBody InvoiceDTO invoiceDTO){
        invoiceService.addInvoice(invoiceDTO);
        isBillPaid();
    }

    public void isBillPaid(){
        Scanner sc=new Scanner(System.in);
        Boolean res=sc.nextBoolean();
        if(res==true){
            invoiceProfit();
        }
        else{
            invoiceLoss();
        }
    }
    public void invoiceProfit(){

    }
    public void invoiceLoss(){

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
