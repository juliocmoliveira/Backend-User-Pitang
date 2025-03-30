package com.example.pitang.user.application.gateway.repository;

import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    boolean emailVerifyIfExists(String email);
    boolean loginVerifyIfExists(String login);
    List<UserEntity> getUsers();
    void createUser(UserEntity userEntity);
}
