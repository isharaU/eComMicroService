package com.ishara.product_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}