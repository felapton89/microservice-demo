package com.microservice.servicecustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice", unique = true)
    private String numberInvoice;

    private String description;

    @Column(name = "create_at")
    private LocalDate creatAt;

    private String state;

    @Column(name = "customer_id")
    private Long customerId;

    @Valid // validamos los items
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<InvoiceItem> items;

    @PrePersist
    public void prePersist(){
        this.creatAt = LocalDate.now();
    }

}
