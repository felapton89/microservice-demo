package com.microservice.servicecustomer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
