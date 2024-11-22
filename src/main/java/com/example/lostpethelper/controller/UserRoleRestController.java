package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.UserRoleDTO;
import com.example.lostpethelper.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-roles")
public class UserRoleRestController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleRestController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles(){
        List<UserRoleDTO> userRoles = userRoleService.findAllUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable Integer id) {
        UserRoleDTO userRoleDTO = userRoleService.findUserRoleById(id);
        return new ResponseEntity<>(userRoleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO createdUser = userRoleService.createUserRole(userRoleDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRoleById(@PathVariable Integer id, @RequestBody UserRoleDTO userRoleDTO){
        UserRoleDTO updatedUserRoleDTO = userRoleService.updateUserRoleById(id, userRoleDTO);
        return new ResponseEntity<>(updatedUserRoleDTO, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRoleDTO> deleteUserRoleById(@PathVariable Integer id) {
        userRoleService.deleteUserRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
