package com.ishara.order_service.service;

import com.ishara.order_service.dto.InventoryResponse;
import com.ishara.order_service.dto.OrderLineItemDto;
import com.ishara.order_service.dto.OrderRequest;
import com.ishara.order_service.model.Order;
import com.ishara.order_service.model.OrderLineItems;
import com.ishara.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call inventory service - let exceptions bubble up to circuit breaker
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .timeout(Duration.ofSeconds(3)) // Timeout for circuit breaker to detect failures
                .block();

        log.info("Inventory response: {}", Arrays.toString(inventoryResponseArray));

        boolean allProductsInStock = inventoryResponseArray != null &&
                Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            log.info("Order placed successfully: {}", order.getOrderNumber());
        } else {
            log.error("Product is not in the Inventory for order: {}", order.getOrderNumber());
            throw new IllegalArgumentException("Product is not in the Inventory, please try again later");
        }
    }

    public void fallbackMethod(OrderRequest orderRequest, Exception exception) {
        log.error("Circuit breaker activated for inventory service. Order request: {}, Error: {}",
                orderRequest.toString(), exception.getMessage());
    }

    private OrderLineItems mapToDto(OrderLineItemDto dto) {
        OrderLineItems item = new OrderLineItems();
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        item.setSkuCode(dto.getSkuCode());
        return item;
    }
}