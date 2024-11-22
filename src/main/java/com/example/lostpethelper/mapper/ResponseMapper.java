package com.example.lostpethelper.mapper;

import com.example.lostpethelper.dto.ResponseDTO;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;

import java.time.OffsetDateTime;

public class ResponseMapper {
    public static ResponseDTO mapToResponseDTO(Response response) {
        return new ResponseDTO(
                response.getMessage(),
                response.getLocation(),
                response.getImgURI(),
                OffsetDateTime.now(), // todo: подумать над другим подходом
                response.getUser().getUserID(),
                response.getTicket().getTicketID()
        );
    }

    public static Response mapToResponse(ResponseDTO responseDTO, Integer id, User user, Ticket ticket) {
        return new Response(
                id,
                responseDTO.message(),
                responseDTO.location(),
                responseDTO.imgURI(),
                responseDTO.createdAt(),
                user,
                ticket
        );
    }
}
