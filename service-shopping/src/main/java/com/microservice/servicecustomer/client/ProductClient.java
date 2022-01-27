package com.microservice.servicecustomer.client;


import com.microservice.servicecustomer.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product", url = "localhost:8090")
public interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}/stock")
    ResponseEntity<?> updateStock(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

}
