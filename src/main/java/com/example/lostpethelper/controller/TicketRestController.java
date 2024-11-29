package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.TicketFromClientDTO;
import com.example.lostpethelper.dto.TicketToClientDTO;
import com.example.lostpethelper.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // метод только для админа
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<TicketToClientDTO>> getAllTickets(){
        List<TicketToClientDTO> tickets = ticketService.findAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TicketToClientDTO> getTicketById(@PathVariable Integer id) {
        TicketToClientDTO ticket = ticketService.findTicketById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TicketToClientDTO> createTicket(@Valid @RequestBody TicketFromClientDTO ticket) {
        TicketToClientDTO createdTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TicketToClientDTO> updateTicketById(@PathVariable Integer id, @Valid @RequestBody TicketFromClientDTO updatedTicket) {
        TicketToClientDTO ticket = ticketService.updateTicketById(id, updatedTicket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable Integer id) {
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
