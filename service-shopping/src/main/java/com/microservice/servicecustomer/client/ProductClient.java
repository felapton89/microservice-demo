package com.microservice.servicecustomer.client;


import com.microservice.servicecustomer.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-product")
@RequestMapping("/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @PatchMapping("/{id}/stock")
    ResponseEntity<?> updateStock(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

}
