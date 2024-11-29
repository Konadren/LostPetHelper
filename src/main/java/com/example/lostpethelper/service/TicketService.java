package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.ticket.TicketFromClientDTO;
import com.example.lostpethelper.dto.ticket.TicketToClientDTO;

import java.util.List;

public interface TicketService {
    List<TicketToClientDTO> findAllTickets();

    TicketToClientDTO findTicketById(Integer id);

    TicketToClientDTO createTicket(TicketFromClientDTO ticket);

    TicketToClientDTO updateTicketById(Integer id, TicketFromClientDTO updatedTicket);

    void deleteTicketById(Integer id);
}
