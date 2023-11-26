package com.kanak.ims.model;

import com.kanak.ims.repository.ProductRepository;
import jakarta.persistence.ManyToOne;

import java.util.Set;

public class Batch {

    private Long id;

    private String BatchNo;

    private Integer quantity;

    @ManyToOne()
    private Set<Product> productSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
