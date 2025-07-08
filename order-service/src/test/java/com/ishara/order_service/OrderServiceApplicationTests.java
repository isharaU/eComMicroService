package com.ishara.order_service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishara.order_service.dto.OrderLineItemDto;
import com.ishara.order_service.dto.OrderRequest;
import com.ishara.order_service.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceApplicationTests {

    @Container
    @SuppressWarnings("resource")
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("order_service")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mysqlContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    @Test
    void testPlaceOrder() throws Exception {
        OrderRequest orderRequest = getOrderRequest();
        String orderRequestString = objectMapper.writeValueAsString(orderRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestString))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("Order is successfully Placed.", result.getResponse().getContentAsString()));
        assertEquals(1, orderRepository.findAll().size());
    }

    private OrderRequest getOrderRequest() {
        OrderLineItemDto orderLineItem = new OrderLineItemDto(1L, "SKU123", BigDecimal.valueOf(100.00), 2);
        return new OrderRequest(Collections.singletonList(orderLineItem));
    }
}

