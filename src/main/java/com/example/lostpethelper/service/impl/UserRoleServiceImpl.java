package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.UserRoleDTO;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.exception.UserRoleNotFoundException;
import com.example.lostpethelper.mapper.UserRoleMapper;
import com.example.lostpethelper.model.UserRole;
import com.example.lostpethelper.repository.UserRoleRepository;
import com.example.lostpethelper.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRoleDTO createUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = UserRoleMapper.mapToUserRole(userRoleDTO, null);
        UserRole createdRole = userRoleRepository.save(userRole);

        return UserRoleMapper.mapToUserRoleDTO(createdRole);
    }

    @Override
    public List<UserRoleDTO> findAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        return userRoles.stream()
                .map(UserRoleMapper::mapToUserRoleDTO)
                .toList();
    }

    @Override
    public UserRoleDTO findUserRoleById(Integer id) {
        return UserRoleMapper.mapToUserRoleDTO(userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Transactional
    @Override
    public UserRoleDTO updateUserRoleById(Integer id, UserRoleDTO userRoleDTO) {
        UserRole existingUserRole = userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id));

        existingUserRole.setRoleName(userRoleDTO.roleName());

        UserRole updatedUserRole = userRoleRepository.save(existingUserRole);

        return UserRoleMapper.mapToUserRoleDTO(updatedUserRole);
    }

    @Override
    public void deleteUserRoleById(Integer id) {
        userRoleRepository.deleteById(id);
    }

}
