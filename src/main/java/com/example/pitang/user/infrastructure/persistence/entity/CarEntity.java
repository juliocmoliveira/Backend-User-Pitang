package com.example.pitang.user.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carro")
public class CarEntity {

    public CarEntity(int year, String licensePlate, String model, String color, UserEntity userEntity) {
        this.year = year;
        this.licensePlate = licensePlate;
        this.model = model;
        this.color = color;
        this.user = userEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ano_fabricacao")
    private int year;

    @NotNull
    @Column(name = "placa")
    private String licensePlate;

    @NotNull
    @Column(name = "modelo")
    private String model;

    @NotNull
    @Column(name = "cor")
    private String color;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;
}
