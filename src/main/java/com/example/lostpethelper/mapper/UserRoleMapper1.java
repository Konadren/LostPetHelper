package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.rest.role.UserRoleRs;
import com.example.lostpethelper.model.UserRole;

public class UserRoleMapper1 {
    public static UserRoleRs mapToUserRoleDTO(UserRole userRole) {
        return new UserRoleRs(userRole.getRoleName());
    }

    public static UserRole mapToUserRole(UserRoleRs userRoleRs, Integer id) {
        return new UserRole(id, userRoleRs.roleName());
    }
}
