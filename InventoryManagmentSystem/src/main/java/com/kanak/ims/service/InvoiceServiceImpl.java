package com.kanak.ims.service;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import com.kanak.ims.model.ProductDetails;
import com.kanak.ims.repository.InvoiceRepository;
import com.kanak.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository innvoicesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice=new Invoice();
        List<ProductDetails> productDetailsList=
                invoiceDTO
                        .getProductDetails()
                        .stream()
                        .map( (pd) ->{
                            ProductDetails productDetails=new ProductDetails();
                            Optional<Product> productOptional=productRepository.findById(pd.getProductPid());
                            if(productOptional.isPresent() && productOptional.get().getQuantity()>=pd.getQty()) {
                                Product p=productOptional.get();
                                p.setQuantity(p.getQuantity()-pd.getQty());
                                productRepository.save(p);
                                productDetails.setProduct(p);
                                productDetails.setQty(pd.getQty());
                            }
                            return productDetails;
                        }).collect(Collectors.toList());
        System.out.println(productDetailsList.get(0).getProduct());
        invoice.setCustomerName(invoiceDTO.getCustomerName());
        invoice.setInnvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setProductDetails(productDetailsList);
        innvoicesRepository.save(invoice);

    }



    @Override
    public List<Product> getTodayInnvoiceDetails() {
        return innvoicesRepository.getDailySaleDetails();
    }

    @Override
    public Long getTodayProfit() {
        return innvoicesRepository.getTodayProfit();
    }

    @Override
    public List<Product> getCustomInvoiceDetails(LocalDate startDate, LocalDate endDate) {
        return innvoicesRepository.getCustomSaleDetails(startDate,endDate);
    }

    @Override
    public Long getCustomProfit(LocalDate startDate, LocalDate endDate) {
        return innvoicesRepository.getCustomProfit(startDate,endDate);
    }

    @Override
    public List<Product> getYearlyInvoiceDetails(int year) {
        return innvoicesRepository.getYearlySaleDetails(year);
    }

    @Override
    public Long getYearlyProfit(int year) {
        return innvoicesRepository.getYearlyProfit(year);
    }
}
