package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.UserDTO;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.exception.UserRoleNotFoundException;
import com.example.lostpethelper.mapper.UserMapper;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.model.UserRole;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.repository.UserRoleRepository;
import com.example.lostpethelper.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        User user = UserMapper.mapToUser(userDTO, null); // криво-косо, почитать про MapStruct
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDTO(savedUser);
    }

//    private UserRole getUserRoleById(UserDTO userDTO) {
//        return userRoleRepository
//                .findById(userDTO.roleId())
//                .orElseThrow(() -> new UserRoleNotFoundException(userDTO.roleId()));
//    }

    @Override
    public UserDTO findUserById(Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public void deleteUserById (Integer id) {
        userRepository.deleteById(id);
        System.out.printf("User with id = %d was deleted%n", id); //todo: логировать
    }

    @Override
    @Transactional
    public UserDTO updateUserById(Integer id, UserDTO userDTO) {
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setName(userDTO.name());
        existingUser.setLastname(userDTO.lastname());
        existingUser.setRoles(userDTO.roles());
        existingUser.setPassword(userDTO.password());
        existingUser.setPhoneNumber(userDTO.phoneNumber());
        existingUser.setEmail(userDTO.email());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::mapToUserDTO)
                .toList();
    }
}
