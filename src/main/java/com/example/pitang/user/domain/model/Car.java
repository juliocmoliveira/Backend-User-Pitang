package com.example.pitang.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private int year;
    private String licensePlate;
    private String model;
    private String color;
}
