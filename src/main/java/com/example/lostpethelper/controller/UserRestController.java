package com.example.lostpethelper.controller;

import com.example.lostpethelper.model.User;
import com.example.lostpethelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController // controller + responseBody
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}") // с помощью @PathVariable берем из запроса id
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping // с помощью @RequestBody спринг создаст объект User на основе данных, переданных в запросе
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User user = userService.updateUserById(id, updatedUser);
        /**
         * Возникает вопрос, как готовить Optional -- на уровне контроллера или сервиса?
         * Выбрал второй вариант, поэтому нужно будет сделать обработчик ошибок
         * Да и в целом разобраться в теме
         */
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //todo: deleteUser()
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
