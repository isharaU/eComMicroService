package com.ishara.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct() {

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getAllProduct() {

    }
}
