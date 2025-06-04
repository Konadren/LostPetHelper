package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.rest.user.UserRq;
import com.example.lostpethelper.dto.rest.user.UserRs;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/users")
public interface UserController {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    UserRs create(@RequestBody @Valid UserRq rq);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    UserRs update(@RequestBody @Valid UserRq rq, @PathVariable String id);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    UserRs get(@PathVariable String id);

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    UserRs getAll();

    @PostMapping("/{id}/remove")
    void delete(@PathVariable String id);
}
