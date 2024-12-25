package com.example.dockerTest.controller;

import com.example.dockerTest.docker.entity.Product;

import com.example.dockerTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    // Получение всех продуктов
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Добавление продукта
    @PostMapping
    public Product addProduct(@RequestBody Product  product) {
        return productService.addProduct(product.getName());
    }


    // Удаление продукта
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
