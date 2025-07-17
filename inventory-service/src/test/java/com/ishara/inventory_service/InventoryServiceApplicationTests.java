package com.ishara.inventory_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishara.inventory_service.model.Inventory;
import com.ishara.inventory_service.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class InventoryServiceApplicationTests {

    @Container
    @SuppressWarnings("resource")
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("inventory_service")
            .withUsername("root")
            .withPassword("root")
            .withReuse(false);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        inventoryRepository.deleteAll();
        Inventory inventory = new Inventory();
        inventory.setSkuCode("SKU123");
        inventoryRepository.save(inventory);
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mysqlContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    @Test
    void testIsInStockWhenProductExists() throws Exception {
        String skuCode = "SKU123";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/" + skuCode))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testIsInStockWhenProductDoesNotExist() throws Exception {
        String skuCode = "NON_EXISTENT_SKU";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/" + skuCode))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
