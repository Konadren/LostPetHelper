package com.example.lostpethelper.controller.impl;

import com.example.lostpethelper.controller.TicketController;
import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;

    @Override
    public TicketRs create(TicketRq rq) {
        return ticketService.create(rq);
    }

    @Override
    public TicketRs update(TicketRq rq, String id) {
        return ticketService.update(id, rq);
    }

    @Override
    public TicketRs get(String id) {
        return ticketService.get(id);
    }

    @Override
    public TicketRs getAll() {
        return ticketService.findALl();
    }

    @Override
    public void delete(String id) {
        ticketService.delete(id);
    }
}
