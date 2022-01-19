package com.microservice.serviceproduct;

import com.microservice.serviceproduct.entity.Category;
import com.microservice.serviceproduct.entity.Product;
import com.microservice.serviceproduct.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("PS5")
                .category(Category.builder().id(1L).build())
                .description("Oh yeah...")
                .stock(10)
                .creatAt(LocalDate.now())
                .price(123.50)
                .status("Created")
                .build();
        productRepository.save(product01);
        List<Product> founds = productRepository.findByCategory(product01.getCategory());
        Assertions.assertThat(founds.size()).isEqualTo(3);
    }

}
