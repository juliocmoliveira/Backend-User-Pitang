package com.example.pitang.user.adapters.gateway.repository;

import com.example.pitang.user.infrastructure.persistence.entity.CarEntity;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import com.example.pitang.user.infrastructure.persistence.repository.UserJpaRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    private UserEntity userEntity;

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
        carEntityList.add(new CarEntity(
                CarMockConstants.CAR_YEAR,
                CarMockConstants.CAR_LICENSE_PLATE,
                CarMockConstants.CAR_MODEL,
                CarMockConstants.CAR_COLOR,
                userEntity
        ));

        userEntity.setCars(carEntityList);
    }

    @Test
    void createUser_shouldSaveUserEntity() {
        when(userJpaRepository.save(userEntity)).thenReturn(userEntity);

        userRepositoryImpl.createUser(userEntity);

        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userJpaRepository).save(userCaptor.capture());

        UserEntity capturedUser = userCaptor.getValue();
        assertEquals(userEntity.getFirstName(), capturedUser.getFirstName());
        assertEquals(userEntity.getLastName(), capturedUser.getLastName());
        assertEquals(userEntity.getEmail(), capturedUser.getEmail());
        assertEquals(userEntity.getBirthday(), capturedUser.getBirthday());
        assertEquals(userEntity.getLogin(), capturedUser.getLogin());
        assertEquals(userEntity.getPassword(), capturedUser.getPassword());
        assertEquals(userEntity.getPhone(), capturedUser.getPhone());
        capturedUser.getCars().forEach(auto -> {
            assertEquals(auto.getYear(), capturedUser.getCars().get(0).getYear());
            assertEquals(auto.getLicensePlate(), capturedUser.getCars().get(0).getLicensePlate());
            assertEquals(auto.getModel(), capturedUser.getCars().get(0).getModel());
            assertEquals(auto.getColor(), capturedUser.getCars().get(0).getColor());
        });

        verify(userJpaRepository, times(1)).save(userEntity);
    }

    @Test
    void emailVerifyIfExists_shouldReturnTrueWhenEmailExists() {
        when(userJpaRepository.existsByEmail(UserMockConstants.EMAIL)).thenReturn(true);

        boolean exists = userRepositoryImpl.emailVerifyIfExists(UserMockConstants.EMAIL);

        verify(userJpaRepository, times(1)).existsByEmail(UserMockConstants.EMAIL);
        assert (exists);
    }

    @Test
    void emailVerifyIfExists_shouldReturnFalseWhenEmailDoesNotExist() {
        when(userJpaRepository.existsByEmail(UserMockConstants.EMAIL)).thenReturn(false);

        boolean exists = userRepositoryImpl.emailVerifyIfExists(UserMockConstants.EMAIL);

        verify(userJpaRepository, times(1)).existsByEmail(UserMockConstants.EMAIL);
        assert (!exists);
    }

    @Test
    void loginVerifyIfExists_shouldReturnTrueWhenLoginExists() {
        when(userJpaRepository.existsByLogin(UserMockConstants.LOGIN)).thenReturn(true);

        boolean exists = userRepositoryImpl.loginVerifyIfExists(UserMockConstants.LOGIN);

        verify(userJpaRepository, times(1)).existsByLogin(UserMockConstants.LOGIN);
        assert (exists);
    }

    @Test
    void loginVerifyIfExists_shouldReturnFalseWhenLoginDoesNotExist() {
        when(userJpaRepository.existsByLogin(UserMockConstants.LOGIN)).thenReturn(false);

        boolean exists = userRepositoryImpl.loginVerifyIfExists(UserMockConstants.LOGIN);

        verify(userJpaRepository, times(1)).existsByLogin(UserMockConstants.LOGIN);
        assert (!exists);
    }
}

