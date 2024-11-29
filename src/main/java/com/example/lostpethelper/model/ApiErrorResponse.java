package com.example.lostpethelper.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ApiErrorResponse {
    private String description;
    private String code;
    private Map<String, String> exceptionName;
}
