package com.microservice.servicecustomer.repository;

import com.microservice.servicecustomer.entity.Customer;
import com.microservice.servicecustomer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByRegion(Region region);
}
