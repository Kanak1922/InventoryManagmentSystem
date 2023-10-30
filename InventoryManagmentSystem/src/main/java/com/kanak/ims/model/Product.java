package com.kanak.ims.model;


import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pid")
        private Long id;
        @Column(name = "product_name")
        private String name;
        @Column(name = "batch_no")
        private String batchNo;
        @Column(name = "purchase_price")
        private double purchasePrice;
        @Column(name = "selling_price")
        private double sellingPrice;
        @Column(name = "quantity")
        private int quantity;
        @Column(name = "manufacturing_date")
        private LocalDate manufacturingDate;
        @Column(name = "expiry_date")
        private LocalDate expiryDate;
        @ManyToOne(cascade = CascadeType.ALL)
        private Category category;

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

        public double getPurchasePrice() {
                return purchasePrice;
        }

        public void setPurchasePrice(double purchasePrice) {
                this.purchasePrice = purchasePrice;
        }

        public double getSellingPrice() {
                return sellingPrice;
        }

        public void setSellingPrice(double sellingPrice) {
                this.sellingPrice = sellingPrice;
        }

        public int getQuantity() {
                return quantity;
        }
        public String getBatchNo() {
                return batchNo;
        }

        public void setBatchNo(String batchNo) {
                this.batchNo = batchNo;
        }


        public void setQuantity(int quantity) {
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
}


