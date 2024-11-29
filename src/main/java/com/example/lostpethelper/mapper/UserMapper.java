package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.user.UserDTO;
import com.example.lostpethelper.dto.user.UserProfileDTO;
import com.example.lostpethelper.model.User;

import java.util.List;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }

    //маппер при регистрации юзера
    public static User mapToUser(UserProfileDTO userDTO, Integer userID) {
        return new User(
                userID,
                userDTO.name(),
                userDTO.lastname(),
                userDTO.phoneNumber(),
                userDTO.email(),
                userDTO.password(),
                "ROLE_USER", // хардкод, потом можно будет сделать грамотно
                List.of(),
                List.of()
        );
    }

    // маппер для личного кабинета
    public static UserProfileDTO mapToUserProfileDTO(User user) {
        return new UserProfileDTO(
                user.getName(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword()
        );
    }



}
