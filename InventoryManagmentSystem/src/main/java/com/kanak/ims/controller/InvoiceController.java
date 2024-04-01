package com.kanak.ims.controller;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.dto.ProductResponseDTO;
import com.kanak.ims.service.InvoiceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    private static final Logger LOGGER= LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping("/user/invoice/todayInvoiceCount")
    public Integer getNoOfInvoiceToday(){
        return invoiceService.getNoOfInvoiceToday();
    }

    @PostMapping("/user/invoice/addInvoice")
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            boolean inv = invoiceService.addInvoice(invoiceDTO);
            if (inv) {
                return new ResponseEntity<>("invoice added sucessfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Invoice not added, please provide correct details",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            LOGGER.error("Error while adding invoice : {}",e.getStackTrace());
            return new ResponseEntity<>("Error while adding invoice",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/invoice/todayInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getDailyInnvoiceDetails() {
        try{
            List<ProductResponseDTO> productResponseDTOList=invoiceService.getTodayInnvoiceDetails();
            if(productResponseDTOList.isEmpty()){
                return new ResponseEntity<>(entryReturn(),HttpStatus.OK);
            }
            return new ResponseEntity<>(productResponseDTOList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while fetching today invoices : {}",e.getMessage());
            return new ResponseEntity<>("Error while fetching today invoices",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @RequestMapping(value = "/admin/invoice/customInvoices", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomInvoiceDetails(@RequestParam("startDate") LocalDate sDate, @RequestParam("endDate") LocalDate eDate) {
        try{
            List<ProductResponseDTO> productResponseDTOList=invoiceService.getCustomInvoiceDetails(sDate, eDate);
            if(productResponseDTOList.isEmpty()){
                return new ResponseEntity<>(entryReturn(),HttpStatus.OK);
            }
            return new ResponseEntity<>(productResponseDTOList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while fetching invoices between custom date range : {}",e.getMessage());
            return new ResponseEntity<>("Error while fetching invoices between custom date range",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "/admin/invoice/yearlyInvoice", method = RequestMethod.GET)
    public ResponseEntity<?> getYearlyInvoiceDetails(@RequestParam("year") int year) {
        try{
            List<ProductResponseDTO> invoiceList=invoiceService.getYearlyInvoiceDetails(year);
            if(invoiceList.isEmpty()){
                return new ResponseEntity<>(entryReturn(),HttpStatus.OK);
            }
            return new ResponseEntity<>(invoiceList,HttpStatus.OK);
        }
        catch (Exception e){
            LOGGER.error("Error while fetching yearly invoices : {}",e.getMessage());
            return new ResponseEntity<>("Error while fetching yearly invoices",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/user/invoice/invoiceCount", method = RequestMethod.GET)
    public int getTodayInvoicesCount(){
        return invoiceService.getNoOfInvoiceToday();
    }

    public List<Object> entryReturn(){
        return new ArrayList<>();
    }


}
