package com.example.pitang.user.utils;

import com.example.pitang.user.domain.model.Car;

import java.util.List;

public final class CarMockConstants {

    public static final int CAR_YEAR = 2018;
    public static final String CAR_LICENSE_PLATE = "PDV-0625";
    public static final String CAR_MODEL = "Audi";
    public static final String CAR_COLOR = "White";
    public static final List<Car> CAR_LIST = List.of(new Car(CAR_YEAR, CAR_MODEL, CAR_LICENSE_PLATE, CAR_COLOR));

    private CarMockConstants() {
        throw new UnsupportedOperationException("Util class");
    }
}
