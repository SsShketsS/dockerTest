package com.example.dockerTest.controller;

import com.example.dockerTest.docker.entity.ProductionWarehouse;
import com.example.dockerTest.service.ProductionWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//POST http://localhost:8080/warehouse?productId=1&quantity=10

@RestController
@RequestMapping("/production_warehouse")
public class ProductionWarehouseController {

    @Autowired
    private ProductionWarehouseService productionWarehouseService;

    // Получение всех записей на складе
    @GetMapping
    public List<ProductionWarehouse> getAllProductionWarehouse() {
        return productionWarehouseService.getAllProductionWarehouse();
    }

    // Добавление записи на склад
    @PostMapping
    public ProductionWarehouse addToProductionWarehouse(@RequestBody Long productionId, @RequestParam int quantity) {
        return productionWarehouseService.addToProductionWarehouse(productionId, quantity);
    }

    // Вычитание количества товара со склада
    @PatchMapping("/subtract")
    public ProductionWarehouse subtractFromProductionWarehouse(@RequestParam Long productionId, @RequestParam int quantity) {
        return productionWarehouseService.subtractFromProductionWarehouse(productionId, quantity);
    }

    // Удаление записи со склада
    @DeleteMapping("/{id}")
    public void deleteFromProductionWarehouse(@PathVariable Long id) {
        productionWarehouseService.deleteFromProductionWarehouse(id);
    }

}
