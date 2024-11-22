package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.TicketDTO;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;

public class TicketMapper {
    public static TicketDTO mapToTicketDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getTicketType(),
                ticket.getPetName(),
                ticket.getDescription(),
                ticket.getLocation(),
                ticket.getImgURI(),
                OffsetDateTime.now(),
                ticket.getUser().getUserID()
        );

    }

    public static Ticket mapToTicket(TicketDTO ticketDTO, Integer id, User user) {
        return new Ticket(
                id,
                ticketDTO.ticketType(),
                ticketDTO.petName(),
                ticketDTO.description(),
                ticketDTO.location(),
                ticketDTO.imgURI(),
                OffsetDateTime.now(), // todo: подумать над этим
                user
        );
    }
}
