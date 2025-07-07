package com.ishara.order_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class OrderController {

    @PostMapping
    private String placeOrder() {
        return "Order is successfully Placed.";
    }
}
