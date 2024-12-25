package com.example.dockerTest.repository;

import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.docker.entity.ProductionComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ProductionCompositionRepository extends JpaRepository<ProductionComposition, Long> {
    List<ProductionComposition> findByProduction(Production production);
}
