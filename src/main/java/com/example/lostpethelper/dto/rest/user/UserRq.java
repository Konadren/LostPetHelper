package com.example.lostpethelper.dto.rest.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRq(
        @NotNull(message = "Name should`t be blank")
        @Size(min = 3, max = 50, message = "Name should be between 3 and 50")
        String name,
        @NotNull(message = "Name should`t be blank")
        @Size(min = 3, max = 50, message = "Name should be between 3 and 50")
        String lastname,
        @Pattern(
                regexp = "\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}",
                message = "Phone number must match the format +7-XXX-XXX-XX-XX"
        )
        String phoneNumber,
        @NotNull(message = "Email can`t be blank")
        @Email(message = "Invalid email format")
        String email
) {
}
