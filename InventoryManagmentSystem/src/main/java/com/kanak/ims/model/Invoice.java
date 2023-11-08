package com.kanak.ims.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private Long invoiceId;
    @Column(name="invoice_date")
    private LocalDate innvoiceDate;
    @Column(name="customer_name")
    private String customerName;

    @ManyToMany
    @JoinTable(
            name = "invoice_product",
            joinColumns = @JoinColumn(name="invoice_id", referencedColumnName="invoice_id"),
            inverseJoinColumns= @JoinColumn(name="product_details_id", referencedColumnName="product_details_id")
    )
    private List<ProductDetails> productDetails;


    public Long getinvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getInnvoiceDate() {
        return innvoiceDate;
    }

    public void setInnvoiceDate(LocalDate innvoiceDate) {
        this.innvoiceDate = innvoiceDate;
    }


    public Long getInvoiceId() {
        return invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
