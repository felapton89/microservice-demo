package com.microservice.servicecustomer.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private int stock;
    private double price;
    private String status;
    private LocalDate creatAt;
    private Category category;

}
