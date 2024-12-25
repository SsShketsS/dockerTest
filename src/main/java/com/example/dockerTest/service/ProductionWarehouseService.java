package com.example.dockerTest.service;


import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.docker.entity.ProductionWarehouse;

import com.example.dockerTest.repository.ProductionRepository;
import com.example.dockerTest.repository.ProductionWarehouseRepository;
import com.example.dockerTest.repository.ProductionCompositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductionWarehouseService {
    @Autowired
    private ProductionWarehouseRepository productionWarehouseRepository;

    @Autowired
    private ProductionRepository productionRepository;


    @Autowired
    private ProductionCompositionRepository productionCompositionRepository;


    // Получение всех записей на складе
    public List<ProductionWarehouse> getAllProductionWarehouse() {
        return productionWarehouseRepository.findAll();
    }

    // Добавление товара на склад
    public ProductionWarehouse addToProductionWarehouse(Long productionId, int quantity) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем, есть ли запись для данного продукта
        ProductionWarehouse existingProductionWarehouse = productionWarehouseRepository.findByProduction(production);

        if (existingProductionWarehouse != null) {
            // Если запись существует, увеличиваем количество
            existingProductionWarehouse.setQuantity(existingProductionWarehouse.getQuantity() + quantity);
            return productionWarehouseRepository.save(existingProductionWarehouse);
        } else {
            // Если записи нет, создаём новую
            ProductionWarehouse productionWarehouse = new ProductionWarehouse(production, quantity);
            return productionWarehouseRepository.save(productionWarehouse);
        }
    }

    // Вычитание количества товара со склада
    public ProductionWarehouse subtractFromProductionWarehouse(Long productionId, int quantity) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем наличие товара на складе
        ProductionWarehouse existingProductionWarehouse = productionWarehouseRepository.findByProduction(production);

        if (existingProductionWarehouse == null) {
            throw new RuntimeException("Product not found in warehouse");
        }

        // Уменьшаем количество
        int newQuantity = existingProductionWarehouse.getQuantity() - quantity;

        if (newQuantity < 0) {
            throw new RuntimeException("Insufficient quantity in warehouse");
        } else if (newQuantity == 0) {
            // Если количество стало нулевым, удаляем запись
            productionWarehouseRepository.delete(existingProductionWarehouse);
            return null; // Или можно вернуть сообщение о том, что запись удалена
        } else {
            // Обновляем количество
            existingProductionWarehouse.setQuantity(newQuantity);
            return productionWarehouseRepository.save(existingProductionWarehouse);
        }
    }

    // Удаление записи со склада
    public void deleteFromProductionWarehouse(Long id) {
        productionWarehouseRepository.deleteById(id);
    }


}
