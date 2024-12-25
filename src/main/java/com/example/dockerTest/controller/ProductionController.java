package com.example.dockerTest.controller;

import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.docker.entity.ProductionComposition;
import com.example.dockerTest.repository.ProductRepository;
import com.example.dockerTest.repository.ProductionCompositionRepository;
import com.example.dockerTest.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/production")
public class ProductionController {

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductionCompositionRepository compositionRepository;

    // Добавление продукции с составом
    @PostMapping
    public Production addProduction(@RequestBody Production production) {
        // Проверка: состав продукции обязателен
        if (production.getCompositions() == null || production.getCompositions().isEmpty()) {
            throw new RuntimeException("Production must have at least one composition.");
        }

        // Проверяем и обновляем каждый элемент состава
        for (ProductionComposition composition : production.getCompositions()) {
            // Проверка на существование продукта
            Product product = productRepository.findById(composition.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: "
                            + composition.getProduct().getId()));

            // Устанавливаем связь с существующим продуктом и продукцией
            composition.setProduct(product);
            composition.setProduction(production);
        }

        // Сохраняем продукцию и состав (если нужно, каскадное сохранение можно оставить)
        return productionRepository.save(production);
    }

    // Получение всех продукций
    @GetMapping
    public List<Production> getAllProductions() {
        return productionRepository.findAll();
    }

    // Получение продукции по ID
    @GetMapping("/{id}")
    public Production getProductionById(@PathVariable Long id) {
        return productionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Production not found"));
    }

    // Получение состава продукции
    @GetMapping("/{productionId}/composition")
    public List<ProductionComposition> getComposition(@PathVariable Long productionId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new RuntimeException("Production not found"));

        return compositionRepository.findByProduction(production);
    }

    // Удаление продукции
    @DeleteMapping("/{id}")
    public void deleteProduction(@PathVariable Long id) {
        productionRepository.deleteById(id);
    }

    // Удаление товара из состава продукции
    @DeleteMapping("/composition/{id}")
    public void removeFromComposition(@PathVariable Long id) {
        compositionRepository.deleteById(id);
    }
}
