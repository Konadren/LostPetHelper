package com.example.lostpethelper.controller.impl;

import com.example.lostpethelper.controller.UserRoleController;
import com.example.lostpethelper.dto.rest.role.UserRoleRs;
import com.example.lostpethelper.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserRoleControllerImpl implements UserRoleController {

    private final UserRoleService userRoleService;

//    // метод только для админа
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles(){
//        List<UserRoleDTO> userRoles = userRoleService.findAllUserRoles();
//        return new ResponseEntity<>(userRoles, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable Integer id) {
//        UserRoleDTO userRoleDTO = userRoleService.findUserRoleById(id);
//        return new ResponseEntity<>(userRoleDTO, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserRoleDTO> createUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
//        UserRoleDTO createdUser = userRoleService.createUserRole(userRoleDTO);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserRoleDTO> updateUserRoleById(@PathVariable Integer id,@Valid @RequestBody UserRoleDTO userRoleDTO){
//        UserRoleDTO updatedUserRoleDTO = userRoleService.updateUserRoleById(id, userRoleDTO);
//        return new ResponseEntity<>(updatedUserRoleDTO, HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UserRoleDTO> deleteUserRoleById(@PathVariable Integer id) {
//        userRoleService.deleteUserRoleById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @Override
    public UserRoleRs create(UserRoleRq rq) {
        return userRoleService.create(rq);
    }

    @Override
    public UserRoleRs update(UserRoleRq rq, String id) {
        return userRoleService.update(id, rq);
    }

    @Override
    public UserRoleRs get(String id) {
        return userRoleService.get(id);
    }

    @Override
    public UserRoleRs getAll() {
        return userRoleService.findAll();
    }

    @Override
    public void delete(String id) {

    }
}
