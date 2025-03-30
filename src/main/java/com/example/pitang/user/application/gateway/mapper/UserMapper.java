package com.example.pitang.user.application.gateway.mapper;

import com.example.pitang.user.application.dto.user.UserResponseDTO;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.infrastructure.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMapper {

    private CarMapper carMapper;

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin(),
                user.getPassword(),
                user.getPhone()
        );

        userEntity.setCars(carMapper.toEntityList(userEntity, user.getCars()));

        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getBirthday(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getPhone(),
                carMapper.toDomainList(userEntity.getCars())
        );
    }

    public UserResponseDTO toDto(User user, Long id) {
        return new UserResponseDTO(
                id,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin(),
                user.getPassword(),
                user.getPhone(),
                carMapper.toDTOList(user.getCars())
        );
    }
}
