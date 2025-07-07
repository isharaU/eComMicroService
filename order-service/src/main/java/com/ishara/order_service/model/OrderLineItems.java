package com.ishara.order_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    private long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
