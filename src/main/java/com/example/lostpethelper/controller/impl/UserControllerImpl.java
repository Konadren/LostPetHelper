package com.example.lostpethelper.controller.impl;

import com.example.lostpethelper.controller.UserController;
import com.example.lostpethelper.dto.rest.user.UserRq;
import com.example.lostpethelper.dto.rest.user.UserRs;
import com.example.lostpethelper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public UserRs create(UserRq rq) {
        return userService.create(rq);
    }

    @Override
    public UserRs update(UserRq rq, String id) {
        return userService.update(id, rq);
    }

    @Override
    public UserRs get(String id) {
        return userService.find(id);
    }

    @Override
    public UserRs getAll() {
        return userService.findAll();
    }

    @Override
    public void delete(String id) {
        userService.delete(id);
    }
}
