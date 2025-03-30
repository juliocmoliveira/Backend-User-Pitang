package com.example.pitang.user.infrastructure.config.exceptions.user;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
