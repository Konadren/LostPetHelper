package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.UserDTO;
import com.example.lostpethelper.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController // controller + responseBody
@RequestMapping("api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}") // с помощью @PathVariable берем из запроса id
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping // с помощью @RequestBody спринг создаст объект User на основе данных, переданных в запросе
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUserById(id, userDTO);
        /**
         * Возникает вопрос, как готовить Optional -- на уровне контроллера или сервиса?
         * Выбрал второй вариант, поэтому нужно будет сделать обработчик ошибок
         * Да и в целом разобраться в теме
         */
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    //todo: deleteUser()
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
