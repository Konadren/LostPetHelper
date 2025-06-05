package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;

public class ResponseMapper1 {
    public static ResponseToClientDTO mapToResponseToClientDTO(Response response) {
        return new ResponseToClientDTO(
                response.getMessage(),
                response.getLocation(),
                response.getImgURI(),
                OffsetDateTime.now(), // todo: подумать над другим подходом
                response.getUser().getUserID(),
                response.getTicket().getTicketID()
        );
    }

    public static Response mapToResponse(ResponseRq responseRq, Integer id, User user, Ticket ticket) {
        return new Response(
                id,
                responseRq.message(),
                responseRq.location(),
                responseRq.imgURI(),
                OffsetDateTime.now(),
                user,
                ticket
        );
    }
}
