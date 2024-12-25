package com.example.dockerTest.service;


import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Warehouse;
import com.example.dockerTest.repository.ProductRepository;
import com.example.dockerTest.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;



    // Получение всех записей на складе
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    // Добавление товара на склад
    public Warehouse addToWarehouse(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем, есть ли запись для данного продукта
        Warehouse existingWarehouse = warehouseRepository.findByProduct(product);

        if (existingWarehouse != null) {
            // Если запись существует, увеличиваем количество
            existingWarehouse.setQuantity(existingWarehouse.getQuantity() + quantity);
            return warehouseRepository.save(existingWarehouse);
        } else {
            // Если записи нет, создаём новую
            Warehouse warehouse = new Warehouse(product, quantity);
            return warehouseRepository.save(warehouse);
        }
    }

    // Вычитание количества товара со склада
    public Warehouse subtractFromWarehouse(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Проверяем наличие товара на складе
        Warehouse existingWarehouse = warehouseRepository.findByProduct(product);

        if (existingWarehouse == null) {
            throw new RuntimeException("Product not found in warehouse");
        }

        // Проверяем, достаточно ли товара для списания
        if (existingWarehouse.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient quantity in warehouse for product ID: "
                    + productId + ". Required: " + quantity + ", Available: "
                    + existingWarehouse.getQuantity());
        }

        // Уменьшаем количество
        int newQuantity = existingWarehouse.getQuantity() - quantity;

        // Если количество становится нулевым, удаляем запись //Надо бы снести это нахер, сделать просто текстовым уведомлением.
        if (newQuantity == 0) {
            warehouseRepository.delete(existingWarehouse);
            return null; // Или можно вернуть сообщение о том, что запись удалена
        } else {
            // Обновляем количество и сохраняем
            existingWarehouse.setQuantity(newQuantity);
            return warehouseRepository.save(existingWarehouse);
        }
    }

    // Удаление записи со склада
    public void deleteFromWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
