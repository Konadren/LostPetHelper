package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.model.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    List<TicketRs> findALl();

    TicketRs get(UUID id);

    TicketRs create(Ticket ticket);

    TicketRs update(UUID id, Ticket updatedTicket);

    void delete(UUID id);
}
