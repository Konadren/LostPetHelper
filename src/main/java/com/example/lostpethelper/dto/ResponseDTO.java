package com.example.lostpethelper.dto;

import java.time.OffsetDateTime;

public record ResponseDTO(
        String message,
        String location,
        String imgURI,
        OffsetDateTime createdAt,
        Integer userId,
        Integer ticketId
) {
}
