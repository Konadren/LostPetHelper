package com.example.lostpethelper.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User with id = " + id + " not found");
    }
}
