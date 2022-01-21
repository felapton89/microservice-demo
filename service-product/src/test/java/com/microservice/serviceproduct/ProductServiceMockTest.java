package com.microservice.serviceproduct;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;
import com.microservice.serviceproduct.repository.ProductRepository;
import com.microservice.serviceproduct.service.ProductService;
import com.microservice.serviceproduct.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(155.55)
                .stock(99)
                .build();
        Mockito
                .when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito
                .when(productRepository.save(computer))
                .thenReturn(computer);
    }

    @Test
    public void whenValidGetId_ThenReturnProduct() {
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {
        Product p = productRepository.findById(1L).get();
        productService.updateStock(1L, 1);
        Assertions.assertThat(p.getStock()).isEqualTo(100);
    }
}
