package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.UserRoleDTO;
import com.example.lostpethelper.model.UserRole;

public class UserRoleMapper {
    public static UserRoleDTO mapToUserRoleDTO(UserRole userRole) {
        return new UserRoleDTO(userRole.getRoleName());
    }

    public static UserRole mapToUserRole(UserRoleDTO userRoleDTO, Integer id) {
        return new UserRole(id, userRoleDTO.roleName());
    }
}
