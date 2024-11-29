package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.response.ResponseFromClientDTO;
import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;

public class ResponseMapper {
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

    public static Response mapToResponse(ResponseFromClientDTO responseFromClientDTO, Integer id, User user, Ticket ticket) {
        return new Response(
                id,
                responseFromClientDTO.message(),
                responseFromClientDTO.location(),
                responseFromClientDTO.imgURI(),
                OffsetDateTime.now(),
                user,
                ticket
        );
    }
}
