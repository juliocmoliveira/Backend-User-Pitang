package com.example.pitang.user.application.usecase;

import com.example.pitang.user.application.gateway.mapper.UserMapper;
import com.example.pitang.user.application.gateway.repository.UserRepository;

public class UseCase {

    final UserRepository userRepository;
    final UserMapper userMapper;

    public UseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
}
