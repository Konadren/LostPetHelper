package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.TicketDTO;
import java.util.List;

public interface TicketService {
    List<TicketDTO> findAllTickets();

    TicketDTO findTicketById(Integer id);

    TicketDTO createTicket(TicketDTO ticket);

    TicketDTO updateTicketById(Integer id, TicketDTO updatedTicket);

    void deleteTicketById(Integer id);
}
