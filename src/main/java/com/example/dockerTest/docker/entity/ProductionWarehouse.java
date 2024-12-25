package com.example.dockerTest.docker.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "product_warehouse")
public class ProductionWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false) // Внешний ключ
    private Production production;



    private int quantity;




    public ProductionWarehouse(){

    }

    public ProductionWarehouse(Production production, int quantity) {
        this.production = production;
        this.quantity = quantity;

    }





    public Long getId() {
        return id;
    }

       public Production getProduction() {
        return production;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Sklad{" +
                "id=" + id +
                ", product=" + production.getName() +
                ", quantity=" + quantity +
                '}';
    }

}
