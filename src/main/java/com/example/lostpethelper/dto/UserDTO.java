package com.example.lostpethelper.dto;

import jakarta.validation.constraints.*;

// не хотелось бы передавать мыло и пароль в открытую
// возможно при дальнейшей доработке проекта придется сделать другой ДТО
// (используемый условно на странице тикета, а этот ДТО использовать в личном кабинете)
public record UserDTO(
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
        String email,
        @NotNull(message = "Password can`t be blank")
        @Size(min = 8, max = 30, message = "Password size should be between 8 and 30")
        String password,
        @NotNull(message = "Roles can`t be null")
        String roles
) {
}
