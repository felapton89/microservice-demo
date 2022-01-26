package com.microservice.servicecustomer.service.impl;

import com.microservice.servicecustomer.client.CustomerClient;
import com.microservice.servicecustomer.client.ProductClient;
import com.microservice.servicecustomer.entity.Invoice;
import com.microservice.servicecustomer.entity.InvoiceItem;
import com.microservice.servicecustomer.model.Customer;
import com.microservice.servicecustomer.model.Product;
import com.microservice.servicecustomer.repository.InvoiceRepository;
import com.microservice.servicecustomer.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CustomerClient customerClient;


    @Override
    public List<Invoice> listAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> listByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice != null) {
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);

            List<InvoiceItem> listItems = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());

            invoice.setItems(listItems);
        }
        return invoice;
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoice.setState("CREATED");
        Invoice invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.updateStock(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
    }

    @Override
    public void editInvoice(Invoice invoice, Long id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        if (optional.isPresent()) {
            Invoice edit = optional.get();
            edit.setDescription(invoice.getDescription());
            if (!edit.getNumberInvoice().equals(invoice.getNumberInvoice())) {
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
