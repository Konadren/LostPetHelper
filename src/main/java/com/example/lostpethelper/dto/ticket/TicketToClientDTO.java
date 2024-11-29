package com.example.lostpethelper.dto.ticket;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record TicketToClientDTO(
        @NotNull(message = "User ID from ticket can`t be empty")
        Integer userID,
        @NotNull(message = "Ticket shouldn`t be empty") //todo: иной подход к тикету сделать
        String ticketType,
        @NotNull(message = "Pet name should`t be empty")
        @Size(min = 3, max = 20, message = "Name should be between 3 and 20")
        String petName,
        @NotNull(message = "Description should`t be empty")
        @Size(min = 10, max = 500, message = "Description should be between 10 and 500")
        String description,
        @NotNull(message = "Location should`t be empty")
        @Size(min = 10, max = 100, message = "Location should be between 10 and 100")
        String location,
        @NotNull(message = "Image field shouldn`t be empty")
        String imgURI,
        @NotNull(message = "Created At field can`t be empty")
        OffsetDateTime createdAt
) {
}