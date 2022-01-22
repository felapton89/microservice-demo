package com.microservice.servicecustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id", nullable = false, unique = true)
    @Size(min = 8, max = 8, message = "The size must be eight digits")
    private String numberID;

    @NotBlank(message = "Write a valid firstname")
    private String firstname;

    @NotBlank(message = "Write a valid lastname")
    private String lastname;

    @Column(unique = true, nullable = false)
    @Email(message = "Write a valid email")
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "Set a valid region")
    private Region region;
}
