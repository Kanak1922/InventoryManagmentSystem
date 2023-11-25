package com.kanak.ims.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class ProductDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="product_details_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private  Product product;

    //@JsonIgnore
    private Integer qty;
    @ManyToMany(mappedBy = "productDetails")
    private List<Invoice> invoiceList;


    public ProductDetails() {
    }

    public ProductDetails(Product product, Integer qty) {
        this.product = product;
        this.qty = qty;
    }

    public ProductDetails(Long id, Product product, Integer qty) {
        this.id = id;
        this.product = product;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
