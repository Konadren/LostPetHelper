package com.example.lostpethelper.controller;

import com.example.lostpethelper.exception.ResponseNotFoundException;
import com.example.lostpethelper.exception.TicketNotFoundException;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.exception.UserRoleNotFoundException;
import com.example.lostpethelper.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            UserNotFoundException.class,
            TicketNotFoundException.class,
            UserRoleNotFoundException.class,
            ResponseNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(Exception e) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .description(e.getClass().getName())
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .exceptionName(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
