package com.example.dockerTest.repository;

import com.example.dockerTest.docker.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository <Product, Long> {

    List<Product> findByName(String name);





}
