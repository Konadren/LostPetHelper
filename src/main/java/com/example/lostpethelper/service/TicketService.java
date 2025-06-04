package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;

import java.util.List;

public interface TicketService {

    List<TicketRs> findALl();

    TicketRs get(Integer id);

    TicketRs create(TicketRq ticket);

    TicketRs update(Integer id, TicketRq updatedTicket);

    void delete(Integer id);
}
