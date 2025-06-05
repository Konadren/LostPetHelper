package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.rest.role.UserRoleRs;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.exception.UserRoleNotFoundException;
import com.example.lostpethelper.mapper.UserRoleMapper1;
import com.example.lostpethelper.model.UserRole;
import com.example.lostpethelper.repository.UserRoleRepository;
import com.example.lostpethelper.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRoleRs create(UserRoleRs userRoleRs) {
        UserRole userRole = UserRoleMapper1.mapToUserRole(userRoleRs, null);
        UserRole createdRole = userRoleRepository.save(userRole);

        return UserRoleMapper1.mapToUserRoleDTO(createdRole);
    }

    @Override
    public List<UserRoleRs> findAll() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        return userRoles.stream()
                .map(UserRoleMapper1::mapToUserRoleDTO)
                .toList();
    }

    @Override
    public UserRoleRs get(Integer id) {
        return UserRoleMapper1.mapToUserRoleDTO(userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    @Override
    public UserRoleRs update(Integer id, UserRoleRs userRoleRs) {
        UserRole existingUserRole = userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id));

        existingUserRole.setRoleName(userRoleRs.roleName());

        UserRole updatedUserRole = userRoleRepository.save(existingUserRole);

        return UserRoleMapper1.mapToUserRoleDTO(updatedUserRole);
    }

    @Override
    public void deleteUserRoleById(Integer id) {
        userRoleRepository.deleteById(id);
    }

}
