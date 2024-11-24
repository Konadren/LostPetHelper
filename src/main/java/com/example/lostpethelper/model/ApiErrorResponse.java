package com.example.lostpethelper.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiErrorResponse {
    private String description;
    private String code;
    private String exceptionName;
}
