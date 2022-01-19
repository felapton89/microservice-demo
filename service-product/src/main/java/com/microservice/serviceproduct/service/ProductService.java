package com.microservice.serviceproduct.service;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    List<Product> findByCategory(Category category);

    Product getProduct(Long id);

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    Product updateStock(Long idProduct, int quantity);

}
