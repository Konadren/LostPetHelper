package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.UserDTO;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.model.UserRole;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getRoleID()
        );
    }

    public static User mapToUser(UserDTO userDTO, UserRole role, Integer userID) {
        return new User(
                userID,
                userDTO.name(),
                userDTO.lastname(),
                userDTO.phoneNumber(),
                userDTO.email(),
                userDTO.password(),
                role
        );
    }



}
