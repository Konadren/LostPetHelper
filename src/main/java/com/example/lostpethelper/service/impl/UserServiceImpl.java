package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.rest.user.UserProfileDTO;
import com.example.lostpethelper.dto.rest.user.UserRs;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.mapper.UserMapper1;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDTO create(UserProfileDTO userDTO) {
        User user = UserMapper1.mapToUser(userDTO, null); // криво-косо, почитать про MapStruct
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        User savedUser = userRepository.save(user);

        return UserMapper1.mapToUserProfileDTO(savedUser);
    }

    @Override
    public UserRs find(Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper1.mapToUserDTO(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
        System.out.printf("User with id = %d was deleted%n", id); //todo: логировать
    }

    @Override
    @Transactional
    public UserProfileDTO update(Integer id, UserProfileDTO userDTO) {
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setName(userDTO.name());
        existingUser.setLastname(userDTO.lastname());
        existingUser.setRoles("ROLE_USER");
        existingUser.setPassword(userDTO.password());
        existingUser.setPhoneNumber(userDTO.phoneNumber());
        existingUser.setEmail(userDTO.email());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper1.mapToUserProfileDTO(updatedUser);
    }

    @Override
    public List<UserRs> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper1::mapToUserDTO)
                .toList();
    }
}
