package com.example.dockerTest.controller;

import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.repository.ProductRepository;
import com.example.dockerTest.repository.ProductionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @Test
    void testAddProductionWithComposition() throws Exception {
        // Шаг 1. Добавляем два продукта: "Кофе" и "Молоко"
        Product coffee = productRepository.save(new Product(null, "Кофе"));
        Product milk = productRepository.save(new Product(null, "Молоко"));

        // Шаг 2. Создаём новую продукцию "Капучино"
        String productionJson = """
            {
                "name": "Капучино"
            }
        """;

        mockMvc.perform(post("/production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Капучино"));

        // Получаем ID добавленной продукции
        Production production = productionRepository.findAll().stream()
                .filter(p -> p.getName().equals("Капучино"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Production not found"));

        // Шаг 3. Добавляем "Кофе" в состав "Капучино"
        mockMvc.perform(post("/production/" + production.getId() + "/addComposition")
                        .param("productId", coffee.getId().toString())
                        .param("quantity", "30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("Кофе"))
                .andExpect(jsonPath("$.quantity").value(30));

        // Шаг 4. Добавляем "Молоко" в состав "Капучино"
        mockMvc.perform(post("/production/" + production.getId() + "/addComposition")
                        .param("productId", milk.getId().toString())
                        .param("quantity", "300"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("Молоко"))
                .andExpect(jsonPath("$.quantity").value(300));


    }
}