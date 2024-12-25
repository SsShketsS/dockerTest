package com.example.dockerTest.service;



import com.example.dockerTest.docker.entity.Manufacture;
import com.example.dockerTest.docker.entity.Production;
import com.example.dockerTest.docker.entity.ProductionComposition;
import com.example.dockerTest.docker.entity.Warehouse;
import com.example.dockerTest.repository.ManufactureRepository;
import com.example.dockerTest.repository.ProductionRepository;
import com.example.dockerTest.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ManufactureService {

//    @Autowired
//    private WarehouseService warehouseService;
//
//    @Autowired
//    private ProductionWarehouseService productionWarehouseService;
//
//    @Autowired
//    private ProductionRepository productionRepository;


    private final WarehouseService warehouseService;
    private final ProductionWarehouseService productionWarehouseService;
    private final ProductionRepository productionRepository;
    private final ManufactureRepository manufactureRepository;


    public Manufacture manufactureProduct(Long productionId, int quantity) {

        //Ищем продукцию по айдишнику
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        //Начинаем списание ингридиентов
        for (ProductionComposition composition : production.getCompositions()){
            Long productId = composition.getProduct().getId();
            int requiredQuantity = composition.getQuantity() * quantity;

            // Списание товара через WarehouseService
            warehouseService.subtractFromWarehouse(productId, requiredQuantity);
        }

        productionWarehouseService.addToProductionWarehouse(productionId, quantity);

        Manufacture manufacture = new Manufacture();
        manufacture.setProduction(production);
        manufacture.setQuantity(quantity);
        manufacture.setCreatedAt(LocalDateTime.now());




        return  manufactureRepository.save(manufacture);


    }





}
