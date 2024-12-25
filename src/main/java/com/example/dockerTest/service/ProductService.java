package com.example.dockerTest.service;


import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Получение всех продуктов
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Добавление нового продукта
    public Product addProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return productRepository.save(product);
    }


    // Удаление продукта
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
