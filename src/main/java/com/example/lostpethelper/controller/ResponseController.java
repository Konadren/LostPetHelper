package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.rest.response.ResponseRq;
import com.example.lostpethelper.dto.rest.response.ResponseRs;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/responses")
public interface ResponseController {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    ResponseRs create(@RequestBody @Valid ResponseRq rq);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseRs update(@RequestBody @Valid ResponseRq rq, @PathVariable String id);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseRs get(@PathVariable String id);

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseRs getAll();

    @PostMapping("/{id}/remove")
    void delete(@PathVariable String id);
}
