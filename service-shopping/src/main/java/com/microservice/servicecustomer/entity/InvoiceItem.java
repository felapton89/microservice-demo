package com.microservice.servicecustomer.entity;

import com.microservice.servicecustomer.model.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "invoice_items")
@Data
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Positive
    private int quantity;

    private double price;

    @Transient
    private double subTotal;

    @Transient
    private Product product;

    public double getSubtotal(){
        if (this.quantity > 0 && this.price > 0){
            return this.quantity * this.price;
        } else {
            return 0;
        }
    }

}
