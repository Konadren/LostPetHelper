package com.example.lostpethelper.exception;

public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException(Integer id) {
        super("User role with id = " + id + " not found");
    }
}
