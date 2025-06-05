package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/tickets")
public interface TicketController {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    TicketRs create(@RequestBody @Valid TicketRq rq);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    TicketRs update(@RequestBody @Valid TicketRq rq, @PathVariable String id);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    TicketRs get(@PathVariable String id);

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    List<TicketRs> getAll();

    @PostMapping("/{id}/remove")
    void delete(@PathVariable String id);
}
