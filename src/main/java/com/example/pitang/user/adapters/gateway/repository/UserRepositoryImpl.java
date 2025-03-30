package com.example.pitang.user.adapters.gateway.repository;

import com.example.pitang.user.application.gateway.repository.UserRepository;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import com.example.pitang.user.infrastructure.persistence.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean emailVerifyIfExists(String email) {
        return this.userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean loginVerifyIfExists(String login) {
        return this.userJpaRepository.existsByLogin(login);
    }

    @Override
    public List<UserEntity> getUsers() {
        return this.userJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void createUser(UserEntity userEntity) {
        this.userJpaRepository.save(userEntity);
    }
}
