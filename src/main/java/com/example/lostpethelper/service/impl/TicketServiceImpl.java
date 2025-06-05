package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.rest.ticket.TicketRq;
import com.example.lostpethelper.dto.rest.ticket.TicketRs;
import com.example.lostpethelper.exception.TicketNotFoundException;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.mapper.TicketMapper1;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public TicketRs create(TicketRq ticketRq) {

        User user = getUserById(ticketRq);
        Ticket ticket = TicketMapper1.mapToTicket(ticketRq, null, user);

        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketMapper1.mapToTicketDTO(savedTicket);
    }

    @Override
    public List<TicketRs> findALl() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(TicketMapper1::mapToTicketDTO)
                .toList();
    }

    @Override
    public TicketRs get(Integer id) {
        Ticket ticket = ticketRepository
                .findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        return TicketMapper1.mapToTicketDTO(ticket);
    }

    @Override
    public TicketRs update(Integer id, TicketRq ticketRq) {
        Ticket existingTicket = ticketRepository
                .findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        User user = getUserById(ticketRq);

        existingTicket.setTicketType(ticketRq.ticketType());
        existingTicket.setUser(user);
        existingTicket.setDescription(ticketRq.description());
        existingTicket.setLocation(ticketRq.location());
        existingTicket.setCreatedAt(OffsetDateTime.now());
        existingTicket.setImgURI(ticketRq.imgURI());
        existingTicket.setPetName(ticketRq.petName());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return TicketMapper1.mapToTicketDTO(updatedTicket);
    }

    @Override
    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }

    private User getUserById(TicketRq ticketRq) {
        return userRepository
                .findById(ticketRq.userID())
                .orElseThrow(() -> new UserNotFoundException(ticketRq.userID()));
    }

}
