package com.microservice.servicecustomer.controller;

import com.microservice.servicecustomer.entity.Invoice;
import com.microservice.servicecustomer.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices(@RequestParam(name = "customerId", required = false) Long customerID) {
        List<Invoice> invoices;
        if (customerID != null) {
            invoices = service.listByCustomerId(customerID);
            if (invoices.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(invoices);
        } else {
            invoices = service.listAllInvoice();
            if (invoices.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(invoices);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") Long id) {
        Invoice invoice = service.getInvoice(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<?> addInvoice(@Valid @RequestBody Invoice invoice) {
        service.addInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editInvoice(@Valid @RequestBody Invoice invoice,
                                         @PathVariable("id") Long id) {
        service.editInvoice(invoice, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") Long id) {
        service.deleteInvoice(id);
        return ResponseEntity.ok().build();
    }


}
