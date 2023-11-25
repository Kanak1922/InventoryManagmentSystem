package com.kanak.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name="sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long saleId;

    @OneToOne(cascade = CascadeType.ALL)
    private Invoice invoice;

    @Column(name="profit")
    private Double profit;


    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setProdcutId(Long saleId) {
        this.saleId = saleId;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
