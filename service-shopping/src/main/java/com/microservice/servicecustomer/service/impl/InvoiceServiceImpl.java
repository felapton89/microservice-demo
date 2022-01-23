package com.microservice.servicecustomer.service.impl;

import com.microservice.servicecustomer.entity.Invoice;
import com.microservice.servicecustomer.repository.InvoiceRepository;
import com.microservice.servicecustomer.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> listAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoice.setState("CREATED");
        invoiceRepository.save(invoice);
    }

    @Override
    public void editInvoice(Invoice invoice, Long id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            Invoice edit = optional.get();
            edit.setDescription(invoice.getDescription());
            if (!edit.getNumberInvoice().equals(invoice.getNumberInvoice())){
                edit.setNumberInvoice(invoice.getNumberInvoice());
            }
            edit.setCustomerId(invoice.getCustomerId());
            edit.getItems().clear();
            edit.setItems(invoice.getItems());
            invoiceRepository.save(edit);
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            Invoice invoice = optional.get();
            invoice.setState("DELETED");
            invoiceRepository.save(invoice);
        }
    }
}
