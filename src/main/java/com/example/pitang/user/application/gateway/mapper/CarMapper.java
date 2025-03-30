package com.example.pitang.user.application.gateway.mapper;

import com.example.pitang.user.application.dto.car.CarResponseDTO;
import com.example.pitang.user.domain.model.Car;
import com.example.pitang.user.infrastructure.persistence.entity.CarEntity;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public class CarMapper {

    public List<CarEntity> toEntityList(UserEntity userEntity, List<Car> carList) {
        return carList.stream()
                .map(car -> new CarEntity(car.getYear(), car.getModel(), car.getLicensePlate(), car.getColor(), userEntity))
                .toList();
    }

    public List<Car> toDomainList(List<CarEntity> carEntityList) {
        return carEntityList.stream()
                .map(carEntity -> new Car(carEntity.getYear(), carEntity.getModel(), carEntity.getLicensePlate(), carEntity.getColor()))
                .toList();
    }

    public List<CarResponseDTO> toDTOList(List<Car> carList) {
        return carList.stream()
                .map(car -> new CarResponseDTO(car.getYear(), car.getModel(), car.getLicensePlate(), car.getColor()))
                .toList();
    }
}
