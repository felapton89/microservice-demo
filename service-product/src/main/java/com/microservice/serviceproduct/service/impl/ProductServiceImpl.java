package com.microservice.serviceproduct.service.impl;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;
import com.microservice.serviceproduct.repository.ProductRepository;
import com.microservice.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Product with id: " + id + " does not exist.")
                );
    }

    @Override
    public void createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatAt(LocalDate.now());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        Product editProduct = productRepository.findById(product.getId())
                .orElseThrow(
                        () -> new IllegalStateException("Product not found")
                );
        editProduct.setName(product.getName());
        editProduct.setDescription(product.getDescription());
        editProduct.setCategory(product.getCategory());
        editProduct.setPrice(product.getPrice());
        productRepository.save(editProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product deleteProduct = productRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Product not found")
                );
        deleteProduct.setStatus("DELETED");
        productRepository.save(deleteProduct);
    }

    @Override
    public Product updateStock(Long idProduct, int quantity) {
        Product updateStockProduct = productRepository.findById(idProduct)
                .orElseThrow(
                        () -> new IllegalStateException("Product not found")
                );
        updateStockProduct.setStock(updateStockProduct.getStock() + quantity);
        return productRepository.save(updateStockProduct);
    }
}
