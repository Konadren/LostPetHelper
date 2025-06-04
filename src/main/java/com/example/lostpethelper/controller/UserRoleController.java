package com.example.lostpethelper.controller;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/roles")
public interface UserRoleController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    UserRoleRs create(@RequestBody @Valid UserRoleRq rq);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    UserRoleRs update(@RequestBody @Valid UserRoleRq rq, @PathVariable String id);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    UserRoleRs get(@PathVariable String id);

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    UserRoleRs getAll();

    @PostMapping("/{id}/remove")
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable String id);
}
