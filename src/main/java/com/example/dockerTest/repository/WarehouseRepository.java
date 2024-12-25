package com.example.dockerTest.repository;


import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseRepository extends JpaRepository <Warehouse, Long> {

    Warehouse findByProduct(Product product);
}
