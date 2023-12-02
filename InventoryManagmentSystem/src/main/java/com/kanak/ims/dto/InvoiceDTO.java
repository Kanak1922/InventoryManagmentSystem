package com.kanak.ims.dto;

import com.fasterxml.jackson.annotation.JsonKey;
import org.antlr.v4.runtime.misc.NotNull;

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

    public List<ProductDetailsDTO> getProductDetailsDTO() {
        return productDetailsDTO;
    }

    public void setProductDetailsDTO(List<ProductDetailsDTO> productDetailsDTO) {
        this.productDetailsDTO = productDetailsDTO;
    }
}
