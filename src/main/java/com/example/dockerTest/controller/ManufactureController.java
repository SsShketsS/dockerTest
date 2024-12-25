package com.example.dockerTest.controller;


import com.example.dockerTest.docker.entity.Manufacture;
import com.example.dockerTest.service.ManufactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacture")
public class ManufactureController {

    private final ManufactureService manufactureService;


    @PostMapping
    public Manufacture produce(@RequestBody ManufactureRequest request) {
        return manufactureService.manufactureProduct(request.getProductionId(), request.getQuantity());
    }
    //Добавил херню
    public static class ManufactureRequest {
        private Long productionId;
        private int quantity;

        // Геттеры и сеттеры
        public Long getProductionId() {
            return productionId;
        }

        public void setProductionId(Long productionId) {
            this.productionId = productionId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

}
