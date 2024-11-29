package com.example.lostpethelper.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResponseFromClientDTO(
        @NotNull(message = "Message should`t be empty")
        @Size(min = 10, max = 500, message = "Message should be between 10 and 500")
        String message,
        @NotNull(message = "Location should`t be empty")
        @Size(min = 10, max = 100, message = "Location should be between 10 and 100")
        String location,
        String imgURI,
//        @NotNull(message = "Created At field can`t be empty")
//        OffsetDateTime createdAt,
        @NotNull(message = "User ID from response can`t be empty")
        Integer userId,
        @NotNull(message = "Ticket ID from response can`t be empty")
        Integer ticketId
) {
}
