package com.example.pitang.user.infrastructure.config.exceptions.user;

public class LoginAlreadyExistsException extends RuntimeException {

    public LoginAlreadyExistsException() {
        super("Login already exists");
    }
}
