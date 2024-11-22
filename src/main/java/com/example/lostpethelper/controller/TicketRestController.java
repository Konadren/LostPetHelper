package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.TicketDTO;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets(){
        List<TicketDTO> tickets = ticketService.findAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Integer id) {
        TicketDTO ticket = ticketService.findTicketById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticket) {
        TicketDTO createdTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicketById(@PathVariable Integer id, @RequestBody TicketDTO updatedTicket) {
        TicketDTO ticket = ticketService.updateTicketById(id, updatedTicket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable Integer id) {
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
