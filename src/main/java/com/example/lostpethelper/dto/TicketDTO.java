package com.example.lostpethelper.dto;

import java.time.OffsetDateTime;

public record TicketDTO(
        String ticketType,
        String petName,
        String description,
        String location,
        String imgURI,
        OffsetDateTime createdAt,
        Integer userID
) {
}
