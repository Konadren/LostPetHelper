package com.example.lostpethelper.service;

import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(int id) {
        ticketRepository.deleteById(id);
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }

}
