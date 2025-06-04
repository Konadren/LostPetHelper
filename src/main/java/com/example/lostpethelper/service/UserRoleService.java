package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.rest.role.UserRoleRs;

import java.util.List;

public interface UserRoleService {

    UserRoleRs create(UserRoleRs userRoleRs);

    List<UserRoleRs> findAll();

    UserRoleRs get(Integer id);

    UserRoleRs update(Integer id, UserRoleRs userRoleRs);

    void delete(Integer id);
}
