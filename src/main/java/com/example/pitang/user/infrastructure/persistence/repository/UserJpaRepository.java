package com.example.pitang.user.infrastructure.persistence.repository;

import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}
