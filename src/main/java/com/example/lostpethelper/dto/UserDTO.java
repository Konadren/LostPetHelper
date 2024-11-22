package com.example.lostpethelper.dto;

// не хотелось бы передавать мыло и пароль в открытую
// возможно при дальнейшей доработке проекта придется сделать другой ДТО
// (используемый условно на странице тикета, а этот ДТО использовать в личном кабинете)
public record UserDTO(
        String name,
        String lastname,
        String phoneNumber,
        String email,
        String password,
        Integer roleId
) {
}
