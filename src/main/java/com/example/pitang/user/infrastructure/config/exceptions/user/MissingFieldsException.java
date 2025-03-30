package com.example.pitang.user.infrastructure.config.exceptions.user;

public class MissingFieldsException extends RuntimeException {

    public MissingFieldsException() {
        super("Missing fields");
    }
}
