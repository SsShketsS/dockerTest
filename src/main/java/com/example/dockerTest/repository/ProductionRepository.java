package com.example.dockerTest.repository;


import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.docker.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Можно еще делить на второй реп
@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

}
