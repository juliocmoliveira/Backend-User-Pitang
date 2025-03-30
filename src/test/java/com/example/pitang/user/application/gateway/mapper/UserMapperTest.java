package com.example.pitang.user.application.gateway.mapper;

import com.example.pitang.user.domain.model.Car;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.infrastructure.persistence.entity.CarEntity;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import com.example.pitang.user.utils.UserMockConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void shouldMapperUserToUserEntity() {

        List<Car> cars = new ArrayList<>();
        List<CarEntity> carEntities = new ArrayList<>();
        when(carMapper.toEntityList(any(), eq(cars))).thenReturn(carEntities);

        User user = new User(
                UserMockConstants.FIRST_NAME,
                UserMockConstants.LAST_NAME,
                UserMockConstants.EMAIL,
                UserMockConstants.BIRTHDAY,
                UserMockConstants.LOGIN,
                UserMockConstants.PASSWORD,
                UserMockConstants.PHONE,
                cars
        );

        UserEntity userEntity = userMapper.toEntity(user);

        assertNotNull(userEntity);
        assertEquals(user.getFirstName(), userEntity.getFirstName());
        assertEquals(user.getLastName(), userEntity.getLastName());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getBirthday(), userEntity.getBirthday());
        assertEquals(user.getLogin(), userEntity.getLogin());
        assertEquals(user.getPassword(), userEntity.getPassword());
        assertEquals(user.getPhone(), userEntity.getPhone());

        assertEquals(carEntities, userEntity.getCars());

        verify(carMapper, times(1)).toEntityList(any(), eq(cars));
    }
}
