package com.kanak.ims.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pid")
        private Long id;
        @Column(name = "product_name")
        private String name;
        @Column(name = "batch_no",unique = true)
        private String batchNo;
        @Column(name = "purchase_price")
        private Double purchasePrice;
        @Column(name = "selling_price")
        private Double sellingPrice;
        @Column(name = "quantity")
        private Integer quantity;
        @Column(name = "manufacturing_date")
        private LocalDate manufacturingDate;
        @Column(name = "expiry_date")
        private LocalDate expiryDate;
        @Column(name="status",columnDefinition =  "VARCHAR(255) DEFAULT 'active'")
        private String status;
        @ManyToOne(cascade = CascadeType.DETACH)
        private Category category;



//        @OneToMany()
//        private List<Batch> batchList;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Double getPurchasePrice() {
                return purchasePrice;
        }

        public void setPurchasePrice(Double purchasePrice) {
                this.purchasePrice = purchasePrice;
        }

        public Double getSellingPrice() {
                return sellingPrice;
        }

        public void setSellingPrice(Double sellingPrice) {
                this.sellingPrice = sellingPrice;
        }

        public Integer getQuantity() {
                return quantity;
        }
        public String getBatchNo() {
                return batchNo;
        }

        public void setBatchNo(String batchNo) {
                this.batchNo = batchNo;
        }


        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public LocalDate getManufacturingDate() {
                return manufacturingDate;
        }

        public void setManufacturingDate(LocalDate manufacturingDate) {
                this.manufacturingDate = manufacturingDate;
        }

        public LocalDate getExpiryDate() {
                return expiryDate;
        }

        public void setExpiryDate(LocalDate expiryDate) {
                this.expiryDate = expiryDate;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
        //        public List<Batch> getBatchList() {
//                return batchList;
//        }
//
//        public void setBatchList(List<Batch> batchList) {
//                this.batchList = batchList;
//        }
}


