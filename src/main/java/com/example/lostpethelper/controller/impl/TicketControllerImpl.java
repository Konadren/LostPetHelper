package com.example.lostpethelper.controller.impl;

import com.example.lostpethelper.controller.TicketController;
import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.mapper.TicketMapper;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;
    private final TicketMapper mapper;

    @Override
    public TicketRs create(TicketRq rq) {

        Ticket ticket = mapper.map(rq);

        return mapper.map(ticketService.create(ticket));
    }

    @Override
    public TicketRs update(TicketRq rq, String id) {

        Ticket ticket = mapper.map(rq);

        return mapper.map(ticketService.update(UUID.fromString(id), ticket));
    }

    @Override
    public TicketRs get(String id) {
        return mapper.map(ticketService.get(UUID.fromString(id)));
    }

    @Override
    public List<TicketRs> getAll() {
        return mapper.map(ticketService.findALl());
    }

    @Override
    public void delete(String id) {
        ticketService.delete(UUID.fromString(id));
    }
}
