package com.kanak.ims.model;

import jakarta.persistence.*;


@Entity
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "category_name")
        private String productCategory;

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
