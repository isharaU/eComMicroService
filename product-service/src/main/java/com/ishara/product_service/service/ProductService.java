package com.ishara.product_service.service;

import com.ishara.product_service.dto.ProductRequest;
import com.ishara.product_service.model.Product;
import com.ishara.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void CreateProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .description(productRequest.getDescription()).build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
}
