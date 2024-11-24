package com.example.lostpethelper.exception;

public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException(Integer id) {
        super("Response with id = " + id + " not found");
    }
}
