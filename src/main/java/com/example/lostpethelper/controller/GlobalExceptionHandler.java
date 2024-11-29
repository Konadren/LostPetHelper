package com.example.lostpethelper.controller;

import com.example.lostpethelper.exception.ResponseNotFoundException;
import com.example.lostpethelper.exception.TicketNotFoundException;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.exception.UserRoleNotFoundException;
import com.example.lostpethelper.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            UserNotFoundException.class,
            TicketNotFoundException.class,
            UserRoleNotFoundException.class,
            ResponseNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NoSuchElementException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getClass().getName(), e.getMessage());

        ApiErrorResponse response = ApiErrorResponse.builder()
                .description(e.getClass().getName())
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .exceptionName(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .description(ex.getClass().getName())
                .code(String.valueOf(HttpStatus.BAD_REQUEST))
                .exceptionName(errors)
                .build();

        return new  ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
