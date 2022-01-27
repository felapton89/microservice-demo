package com.microservice.servicecustomer.client;

import com.microservice.servicecustomer.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-customer", url = "localhost:8091")
public interface CustomerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);

}
