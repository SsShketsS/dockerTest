package com.example.dockerTest.controller;


import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Warehouse;
import com.example.dockerTest.repository.ProductRepository;
import com.example.dockerTest.repository.WarehouseRepository;
import com.example.dockerTest.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    //mapstruct  к проекту и разделить адаптера(контролеров)

    @Autowired
    private WarehouseService warehouseService;

    // Получение всех записей на складе
    @GetMapping
    public List<Warehouse> getAllWarehouse() {
        return warehouseService.getAllWarehouse();
    }

    @PostMapping
    public Warehouse addToWarehouse(@RequestBody AddWarehouseRequest request) {
        return warehouseService.addToWarehouse(request.getProductId(), request.getQuantity());
    }

    // DTO для JSON-запроса
    public static class AddWarehouseRequest {
        private Long productId;
        private int quantity;

        // Геттеры и сеттеры
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Вычитание количества товара со склада
    @PatchMapping("/subtract")
    public Warehouse subtractFromWarehouse(@RequestParam Long productId, @RequestParam int quantity) {
        return warehouseService.subtractFromWarehouse(productId, quantity);
    }

    // Удаление записи со склада
    @DeleteMapping("/{id}")
    public void deleteFromWarehouse(@PathVariable Long id) {
        warehouseService.deleteFromWarehouse(id);
    }
}
