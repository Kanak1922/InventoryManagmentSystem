package com.kanak.ims.dto;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDTO {
    private LocalDate invoiceDate;
    private String customerName;
    private List<ProductDetailsDTO> productDetailsDTO;

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ProductDetailsDTO> getProductDetails() {
        return productDetailsDTO;
    }

    public void setProductDetails(List<ProductDetailsDTO> productDetails) {
        this.productDetailsDTO = productDetails;
    }
}
