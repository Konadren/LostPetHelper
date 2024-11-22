package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleService {

    UserRoleDTO createUserRole(UserRoleDTO userRoleDTO);

    List<UserRoleDTO> findAllUserRoles();

    UserRoleDTO findUserRoleById(Integer id);

    UserRoleDTO updateUserRoleById(Integer id, UserRoleDTO userRoleDTO);

    void deleteUserRoleById(Integer id);
}
