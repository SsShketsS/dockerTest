package com.example.dockerTest.repository;

import com.example.dockerTest.docker.entity.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
}