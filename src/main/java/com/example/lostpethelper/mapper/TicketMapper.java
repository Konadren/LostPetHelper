package com.example.lostpethelper.mapper;

import com.example.lostpethelper.config.MapStructConfig;
import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface TicketMapper {

    TicketRs map(Ticket source);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", source = "")
    Ticket map(TicketRq source);
}
