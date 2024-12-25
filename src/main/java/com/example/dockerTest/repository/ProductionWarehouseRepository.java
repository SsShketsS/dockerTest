package com.example.dockerTest.repository;


import com.example.dockerTest.docker.entity.ProductionWarehouse;
import com.example.dockerTest.docker.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductionWarehouseRepository extends JpaRepository<ProductionWarehouse, Long> {


    ProductionWarehouse findByProduction(Production Production);

}
