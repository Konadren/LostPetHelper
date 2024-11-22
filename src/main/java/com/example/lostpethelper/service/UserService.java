package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    UserDTO findUserById(Integer id);

    UserDTO createUser(UserDTO user);

    UserDTO updateUserById(Integer id, UserDTO userDTO);

    void deleteUserById(Integer id);
}
