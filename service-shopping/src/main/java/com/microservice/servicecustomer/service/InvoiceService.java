package com.microservice.servicecustomer.service;

import com.microservice.servicecustomer.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> listAllInvoice();

    Invoice getInvoice(Long id);

    void addInvoice(Invoice invoice);

    void editInvoice(Invoice invoice, Long id);

    void deleteInvoice(Long id);

}
