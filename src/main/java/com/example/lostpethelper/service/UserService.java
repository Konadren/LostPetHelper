package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.user.UserDTO;
import com.example.lostpethelper.dto.user.UserProfileDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    UserDTO findUserById(Integer id);

    UserProfileDTO createUser(UserProfileDTO user);

    UserProfileDTO updateUserById(Integer id, UserProfileDTO userDTO);

    void deleteUserById(Integer id);
}
