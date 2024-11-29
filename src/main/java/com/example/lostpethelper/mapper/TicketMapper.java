package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.dto.ticket.TicketFromClientDTO;
import com.example.lostpethelper.dto.ticket.TicketToClientDTO;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;
import java.util.List;

public class TicketMapper {
    public static TicketToClientDTO mapToTicketDTO(Ticket ticket) {
        return new TicketToClientDTO(
                ticket.getUser().getUserID(),
                ticket.getTicketType(),
                ticket.getPetName(),
                ticket.getDescription(),
                ticket.getLocation(),
                ticket.getImgURI(),
                ticket.getCreatedAt()
        );

    }

    public static Ticket mapToTicket(TicketFromClientDTO ticketFromClientDTO, Integer id,
                                     User user) {
        return new Ticket(
                id,
                user,
                ticketFromClientDTO.ticketType(),
                ticketFromClientDTO.petName(),
                ticketFromClientDTO.description(),
                ticketFromClientDTO.location(),
                ticketFromClientDTO.imgURI(),
                OffsetDateTime.now(), // todo: подумать над этим
                List.of() // todo: хардкод
        );
    }
}
