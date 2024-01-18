package com.kanak.ims.controller;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    @PostMapping("/addInvoice")
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        boolean inv=invoiceService.addInvoice(invoiceDTO);
        if(inv){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @RequestMapping(value = "/todayInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getDailyInnvoiceDetails() {
        return ResponseEntity.ok(invoiceService.getTodayInnvoiceDetails());
    }



    @RequestMapping(value = "/customInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomInvoiceDetails(@RequestParam("startDate") LocalDate sDate, @RequestParam("endDate") LocalDate eDate) {
        return ResponseEntity.ok(invoiceService.getCustomInvoiceDetails(sDate, eDate));
    }



    @RequestMapping(value = "/yearlyInvoice", method = RequestMethod.GET)
    public ResponseEntity<?> getYearlyInvoiceDetails(@RequestParam("year") int year) {
        return ResponseEntity.ok(invoiceService.getYearlyInvoiceDetails(year));
    }


}
