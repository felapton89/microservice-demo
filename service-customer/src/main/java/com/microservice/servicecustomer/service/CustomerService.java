package com.microservice.servicecustomer.service;

import com.microservice.servicecustomer.entity.Customer;
import com.microservice.servicecustomer.entity.Region;

import java.util.List;

public interface CustomerService {

    List<Customer> listAllCustomer();

    List<Customer> findByRegion(Region region);

    Customer getCustomer(Long id);

    void addCustomer(Customer customer);

    void editCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);

}
