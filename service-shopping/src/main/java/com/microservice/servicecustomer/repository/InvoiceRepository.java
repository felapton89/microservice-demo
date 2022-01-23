package com.microservice.servicecustomer.repository;

import com.microservice.servicecustomer.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomerId(Long id);

    Invoice findByNumberInvoice(String numberInvoice);
}
