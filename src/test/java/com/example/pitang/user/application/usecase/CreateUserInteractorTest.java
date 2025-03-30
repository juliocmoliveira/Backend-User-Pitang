package com.example.pitang.user.application.usecase;

import com.example.pitang.user.application.gateway.mapper.UserMapper;
import com.example.pitang.user.application.gateway.repository.UserRepository;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.infrastructure.config.exceptions.user.EmailAlreadyExistsException;
import com.example.pitang.user.infrastructure.config.exceptions.user.LoginAlreadyExistsException;
import com.example.pitang.user.infrastructure.persistence.entity.CarEntity;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import com.example.pitang.user.utils.CarMockConstants;
import com.example.pitang.user.utils.UserMockConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserInteractorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CreateUserInteractor createUserInteractor;

    private UserEntity userEntity;
    private User user;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity(
                UserMockConstants.FIRST_NAME,
                UserMockConstants.LAST_NAME,
                UserMockConstants.EMAIL,
                UserMockConstants.BIRTHDAY,
                UserMockConstants.LOGIN,
                UserMockConstants.PASSWORD,
                UserMockConstants.PHONE
        );

        List<CarEntity> carEntityList = new ArrayList<>();
        carEntityList.add(
                new CarEntity(
                        CarMockConstants.CAR_YEAR,
                        CarMockConstants.CAR_LICENSE_PLATE,
                        CarMockConstants.CAR_MODEL,
                        CarMockConstants.CAR_COLOR,
                        userEntity
                )
        );

        userEntity.setCars(carEntityList);

        user = new User(
                UserMockConstants.FIRST_NAME,
                UserMockConstants.LAST_NAME,
                UserMockConstants.EMAIL,
                UserMockConstants.BIRTHDAY,
                UserMockConstants.LOGIN,
                UserMockConstants.PASSWORD,
                UserMockConstants.PHONE,
                CarMockConstants.CAR_LIST
        );
    }

    @Test
    void shouldThrowExceptionWhenUserMockConstants() {
        when(userRepository.emailVerifyIfExists(UserMockConstants.EMAIL)).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> createUserInteractor.createUser(user));
    }

    @Test
    void shouldThrowExceptionWhenLoginAlreadyExists() {
        when(userRepository.loginVerifyIfExists(UserMockConstants.LOGIN)).thenReturn(true);

        assertThrows(LoginAlreadyExistsException.class, () -> createUserInteractor.createUser(user));
    }

    @Test
    void shouldCreateUserSucessfully() {
        when(userRepository.emailVerifyIfExists(UserMockConstants.EMAIL)).thenReturn(false);
        when(userRepository.loginVerifyIfExists(UserMockConstants.LOGIN)).thenReturn(false);
        when(userMapper.toEntity(user)).thenReturn(userEntity);

        createUserInteractor.createUser(user);

        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).createUser(userCaptor.capture());

        UserEntity capturedUser = userCaptor.getValue();
        assertEquals(user.getFirstName(), capturedUser.getFirstName());
        assertEquals(user.getLastName(), capturedUser.getLastName());
        assertEquals(user.getEmail(), capturedUser.getEmail());
        assertEquals(user.getBirthday(), capturedUser.getBirthday());
        assertEquals(user.getLogin(), capturedUser.getLogin());
        assertEquals(user.getPassword(), capturedUser.getPassword());
        assertEquals(user.getPhone(), capturedUser.getPhone());
        capturedUser.getCars().forEach(auto -> {
            assertEquals(auto.getYear(), capturedUser.getCars().get(0).getYear());
            assertEquals(auto.getLicensePlate(), capturedUser.getCars().get(0).getLicensePlate());
            assertEquals(auto.getModel(), capturedUser.getCars().get(0).getModel());
            assertEquals(auto.getColor(), capturedUser.getCars().get(0).getColor());
        });
    }
}
