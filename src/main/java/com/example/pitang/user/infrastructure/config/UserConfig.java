package com.example.pitang.user.infrastructure.config;

import com.example.pitang.user.adapters.gateway.repository.UserRepositoryImpl;
import com.example.pitang.user.application.gateway.mapper.CarMapper;
import com.example.pitang.user.application.gateway.mapper.UserMapper;
import com.example.pitang.user.application.gateway.repository.UserRepository;
import com.example.pitang.user.application.usecase.CreateUserInteractor;
import com.example.pitang.user.application.usecase.GetUserInteractor;
import com.example.pitang.user.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CarMapper createCarMapper() {
        return new CarMapper();
    }

    @Bean
    UserMapper returnMapper(CarMapper carMapper) {
        return new UserMapper(carMapper);
    }

    @Bean
    CreateUserInteractor createUserInteractor(UserRepository userRepository, UserMapper userMapper) {
        return new CreateUserInteractor(userRepository, userMapper);
    }

    @Bean
    UserRepositoryImpl createUserRepositoryImpl(UserJpaRepository userJpaRepository) {
        return new UserRepositoryImpl(userJpaRepository);
    }

    @Bean
    GetUserInteractor getUserInteractor(UserRepository userRepository, UserMapper userMapper) {
        return new GetUserInteractor(userRepository, userMapper);
    }
}
