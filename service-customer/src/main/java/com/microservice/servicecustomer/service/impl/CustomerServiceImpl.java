package com.microservice.servicecustomer.service.impl;

import com.microservice.servicecustomer.entity.Customer;
import com.microservice.servicecustomer.entity.Region;
import com.microservice.servicecustomer.repository.CustomerRepository;
import com.microservice.servicecustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> listAllCustomer() {
        return repository.findAll();
    }

    @Override
    public List<Customer> findByRegion(Region region) {
        return repository.findByRegion(region);
    }

    @Override
    public Customer getCustomer(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setState("CREATED");
        repository.save(customer);
    }

    @Override
    public void editCustomer(Customer customer, Long id) {
        Optional<Customer> optional = repository.findById(id);
        if (optional.isPresent()) {
            Customer edit = optional.get();
            edit.setFirstname(customer.getFirstname());
            edit.setLastname(customer.getLastname());
            edit.setEmail(customer.getEmail());
            edit.setNumberID(customer.getNumberID());
            edit.setPhotoUrl(customer.getPhotoUrl());
            repository.save(edit);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer not found"));
        customer.setState("DELETED");
        repository.save(customer);
    }
}
