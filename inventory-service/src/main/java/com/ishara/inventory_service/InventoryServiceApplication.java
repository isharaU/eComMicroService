package com.ishara.inventory_service;

import com.ishara.inventory_service.model.Inventory;
import com.ishara.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventoryOne = new Inventory();
            inventoryOne.setSkuCode("Samsung A55");
            inventoryOne.setQuantity(100);

            Inventory inventoryTwo = new Inventory();
            inventoryTwo.setSkuCode("I phone 13");
            inventoryTwo.setQuantity(300);

            inventoryRepository.save(inventoryOne);
            inventoryRepository.save(inventoryTwo);
        };
    }

}
