package com.microservice.serviceproduct.repository;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
