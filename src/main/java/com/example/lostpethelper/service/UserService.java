package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.rest.user.UserRs;
import com.example.lostpethelper.dto.rest.user.UserProfileDTO;

import java.util.List;

public interface UserService {

    List<UserRs> findAll();

    UserRs find(Integer id);

    UserProfileDTO create(UserProfileDTO user);

    UserProfileDTO update(Integer id, UserProfileDTO userDTO);

    void delete(Integer id);
}
