package com.microservice.serviceproduct.service.impl;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;
import com.microservice.serviceproduct.repository.ProductRepository;
import com.microservice.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
                .orElse(
                        null
                );
    }

    @Override
    public void createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatAt(LocalDate.now());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product editProduct = productRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Product with id: " + id + " not found")
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
                        () -> new NoSuchElementException("Product with id: " + id + " not found")
                );
        deleteProduct.setStatus("DELETED");
        productRepository.save(deleteProduct);
    }

    @Override
    public void updateStock(Long idProduct, int quantity) {
        Product updateStockProduct = productRepository.findById(idProduct)
                .orElseThrow(
                        () -> new IllegalStateException("Product not found")
                );
        if ((updateStockProduct.getStock() + quantity) < 0) {
            throw new ValidationException("Not enough stock");
        } else {
            updateStockProduct.setStock(updateStockProduct.getStock() + quantity);
            productRepository.save(updateStockProduct);
        }
    }
}
