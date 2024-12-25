package com.example.dockerTest.docker.entity;




import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production; // Производимая продукция

    @Column(name = "quantity", nullable = false)
    private int quantity; // Количество произведённой продукции

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Дата и время производства

    // Конструкторы, геттеры и сеттеры
    public Manufacture() {}

    // Конструктор с параметрами (без id)
    public Manufacture(Production production, int quantity, LocalDateTime createdAt) {
        this.production = production;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }




    public Production getProduction() {
        return production;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
