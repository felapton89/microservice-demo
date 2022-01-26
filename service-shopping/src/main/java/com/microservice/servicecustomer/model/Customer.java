package com.microservice.servicecustomer.model;

import lombok.Data;

@Data
public class Customer {

    private Long id;
    private String numberID;
    private String firstname;
    private String lastname;
    private String email;
    private String photoUrl;
    private String state;
    private Region region;

}
