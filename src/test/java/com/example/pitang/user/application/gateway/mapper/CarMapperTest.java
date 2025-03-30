package com.example.pitang.user.application.gateway.mapper;

import com.example.pitang.user.domain.model.Car;
import com.example.pitang.user.infrastructure.persistence.entity.CarEntity;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import com.example.pitang.user.utils.CarMockConstants;
import com.example.pitang.user.utils.UserMockConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {

    public static final String LICENSE = "Model S";
    private CarMapper carMapper;

    private UserEntity userEntity;
    private List<Car> carList;

    @BeforeEach
    void setUp() {
        carMapper = new CarMapper();

        userEntity = new UserEntity(UserMockConstants.FIRST_NAME, UserMockConstants.LAST_NAME, UserMockConstants.EMAIL, UserMockConstants.BIRTHDAY, UserMockConstants.LOGIN, UserMockConstants.PASSWORD, UserMockConstants.PHONE);

        carList = new ArrayList<>();
        carList.add(new Car(CarMockConstants.CAR_YEAR, CarMockConstants.CAR_MODEL, CarMockConstants.CAR_LICENSE_PLATE, CarMockConstants.CAR_COLOR));
        carList.add(new Car(CarMockConstants.CAR_YEAR, CarMockConstants.CAR_MODEL, CarMockConstants.CAR_LICENSE_PLATE, CarMockConstants.CAR_COLOR));
    }

    @Test
    void testToEntityList() {
        List<CarEntity> carEntities = carMapper.toEntityList(userEntity, carList);

        assertNotNull(carEntities);
        assertEquals(2, carEntities.size());

        CarEntity carEntity1 = carEntities.get(0);
        assertEquals(CarMockConstants.CAR_YEAR, carEntity1.getYear());
        assertEquals(CarMockConstants.CAR_MODEL, carEntity1.getModel());
        assertEquals(CarMockConstants.CAR_LICENSE_PLATE, carEntity1.getLicensePlate());
        assertEquals(CarMockConstants.CAR_COLOR, carEntity1.getColor());
        assertEquals(userEntity, carEntity1.getUser());

        CarEntity carEntity2 = carEntities.get(1);
        assertEquals(CarMockConstants.CAR_YEAR, carEntity2.getYear());
        assertEquals(CarMockConstants.CAR_MODEL, carEntity2.getModel());
        assertEquals(CarMockConstants.CAR_LICENSE_PLATE, carEntity2.getLicensePlate());
        assertEquals(CarMockConstants.CAR_COLOR, carEntity2.getColor());
        assertEquals(userEntity, carEntity2.getUser());
    }
}
