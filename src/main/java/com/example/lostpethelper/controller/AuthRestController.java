package com.example.lostpethelper.controller;


import com.example.lostpethelper.dto.user.UserDTO;
import com.example.lostpethelper.dto.user.UserProfileDTO;
import com.example.lostpethelper.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//todo: доделать AuthController + подумать, мейби это РЕСТ контроллер
@RestController
@RequestMapping("api/auth")
public class AuthRestController {

    private final UserService userService;

    @Autowired
    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    //todo: почему-то при создании юзера с паролем, который не проходит ошибку, выдает 401 authorized
    @PostMapping
    public ResponseEntity<UserProfileDTO> createUser(@Valid @RequestBody UserProfileDTO userDTO) {
        UserProfileDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
