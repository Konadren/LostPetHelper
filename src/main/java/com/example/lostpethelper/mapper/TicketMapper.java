package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;
import java.util.List;

public class TicketMapper {
    public static TicketRs mapToTicketDTO(Ticket ticket) {
        return new TicketRs(
                ticket.getUser().getUserID(),
                ticket.getTicketType(),
                ticket.getPetName(),
                ticket.getDescription(),
                ticket.getLocation(),
                ticket.getImgURI(),
                ticket.getCreatedAt()
        );

    }

    public static Ticket mapToTicket(TicketRq ticketRq, Integer id,
                                     User user) {
        return new Ticket(
                id,
                user,
                ticketRq.ticketType(),
                ticketRq.petName(),
                ticketRq.description(),
                ticketRq.location(),
                ticketRq.imgURI(),
                OffsetDateTime.now(), // todo: подумать над этим
                List.of() // todo: хардкод
        );
    }
}
