package com.kanak.ims.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String productCategory;

        private Long noOfProduct;

        public Long getNoOfProduct() {
                return noOfProduct;
        }

        public void setNoOfProduct(Long noOfProduct) {
                this.noOfProduct = noOfProduct;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getProductCategory() {
                return productCategory;
        }

        public void setProductCategory(String productCategory) {
                this.productCategory = productCategory;
        }
}
