package com.microservice.servicecustomer.client;

import com.microservice.servicecustomer.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-customer")
@RequestMapping("/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);

}
