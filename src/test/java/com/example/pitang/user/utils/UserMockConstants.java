package com.example.pitang.user.utils;

import java.time.LocalDate;

public final class UserMockConstants {

    public static final String FIRST_NAME = "Hello";
    public static final String LAST_NAME = "World";
    public static final String EMAIL = "hello@world.com";
    public static final String LOGIN = "hello.world";
    public static final String PASSWORD = "h3ll0";
    public static final String PHONE = "9888888888";
    public static final LocalDate BIRTHDAY = LocalDate.of(90, 7, 17);

    private UserMockConstants() {
        throw new UnsupportedOperationException("Utils class");
    }

}
