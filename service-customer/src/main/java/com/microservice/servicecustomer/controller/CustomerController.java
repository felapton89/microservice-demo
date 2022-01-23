package com.microservice.servicecustomer.controller;

import com.microservice.servicecustomer.entity.Customer;
import com.microservice.servicecustomer.entity.Region;
import com.microservice.servicecustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers(@RequestParam(name = "regionId", required = false) Long regionId) {
        List<Customer> listCustomers;
        if (regionId == null) {
            listCustomers = customerService.listAllCustomer();
            if (listCustomers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            listCustomers = customerService.findByRegion(Region.builder().id(regionId).build());
            if (listCustomers.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(listCustomers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@Valid @RequestBody Customer customer,
                                          @PathVariable("id") Long idCustomer) {
        customerService.editCustomer(customer, idCustomer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
