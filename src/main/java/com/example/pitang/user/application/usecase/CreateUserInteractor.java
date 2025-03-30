package com.example.pitang.user.application.usecase;


import com.example.pitang.user.application.gateway.mapper.UserMapper;
import com.example.pitang.user.application.gateway.repository.UserRepository;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.infrastructure.config.exceptions.user.EmailAlreadyExistsException;
import com.example.pitang.user.infrastructure.config.exceptions.user.LoginAlreadyExistsException;

public class CreateUserInteractor extends UseCase {

    public CreateUserInteractor(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }

    public void createUser(User user) {
        this.loginVerifyIfExists(user.getLogin());
        this.emailVerifyIfExists(user.getEmail());
        this.userRepository.createUser(this.userMapper.toEntity(user));
    }

    private void emailVerifyIfExists(String email) throws EmailAlreadyExistsException {
        if (this.userRepository.emailVerifyIfExists(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void loginVerifyIfExists(String login) throws LoginAlreadyExistsException {
        if (this.userRepository.loginVerifyIfExists(login)) {
            throw new LoginAlreadyExistsException();
        }
    }
}
