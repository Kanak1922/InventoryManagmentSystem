package com.kanak.ims.controller;

import java.time.LocalDate;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;

    public Product() {
    }

    public Product(Long id, String name, Double price, Integer quantity, LocalDate manufacturingDate, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
    }

    // getters and setters

}
