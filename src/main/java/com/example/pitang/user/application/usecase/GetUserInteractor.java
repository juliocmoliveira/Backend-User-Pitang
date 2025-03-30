package com.example.pitang.user.application.usecase;

import com.example.pitang.user.application.dto.user.UserResponseDTO;
import com.example.pitang.user.application.gateway.mapper.UserMapper;
import com.example.pitang.user.application.gateway.repository.UserRepository;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public class GetUserInteractor extends UseCase {

    public GetUserInteractor(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }

    public List<UserResponseDTO> get() {
        List<UserEntity> userEntityList = this.userRepository.getUsers();
        List<User> userList = userEntityList.stream().map(userMapper::toDomain).toList();
        return userList.stream()
                .map(user -> userMapper.toDto(user, getUserIdFromEntity(user, userEntityList))).toList();
    }

    private Long getUserIdFromEntity(User user, List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .filter(entity -> entity.getLogin().equals(user.getLogin()) && entity.getEmail().equals(user.getEmail()))
                .map(UserEntity::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
