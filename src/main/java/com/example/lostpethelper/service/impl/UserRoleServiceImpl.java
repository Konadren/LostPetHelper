package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.UserRoleDTO;
import com.example.lostpethelper.mapper.UserRoleMapper;
import com.example.lostpethelper.model.UserRole;
import com.example.lostpethelper.repository.UserRoleRepository;
import com.example.lostpethelper.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    //todo: CRUD

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
                .orElseThrow(() -> new NoSuchElementException("User role not found")));
    }

    @Transactional
    @Override
    public UserRoleDTO updateUserRoleById(Integer id, UserRoleDTO userRoleDTO) {
        UserRole existingUserRole = userRoleRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User role not found"));

        existingUserRole.setRoleName(userRoleDTO.roleName());

        UserRole updatedUserRole = userRoleRepository.save(existingUserRole);

        return UserRoleMapper.mapToUserRoleDTO(updatedUserRole);
    }

    @Override
    public void deleteUserRoleById(Integer id) {
        userRoleRepository.deleteById(id);
    }

}
