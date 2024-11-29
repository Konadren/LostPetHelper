package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.ticket.TicketFromClientDTO;
import com.example.lostpethelper.dto.ticket.TicketToClientDTO;
import com.example.lostpethelper.exception.TicketNotFoundException;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.mapper.TicketMapper;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public TicketToClientDTO createTicket(TicketFromClientDTO ticketFromClientDTO) {
        User user = getUserById(ticketFromClientDTO);


        // List<Response> responses
        Ticket ticket = TicketMapper.mapToTicket(ticketFromClientDTO, null, user);

        System.out.println(ticket);
        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketMapper.mapToTicketDTO(savedTicket);
    }

    @Override
    public List<TicketToClientDTO> findAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(TicketMapper::mapToTicketDTO)
                .toList();
    }

    @Override
    public TicketToClientDTO findTicketById(Integer id) {
        Ticket ticket = ticketRepository
                .findById(id)
                .orElseThrow(() ->  new TicketNotFoundException(id));

        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public TicketToClientDTO updateTicketById(Integer id, TicketFromClientDTO ticketFromClientDTO){
        Ticket existingTicket = ticketRepository
                .findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        User user = getUserById(ticketFromClientDTO);

        existingTicket.setTicketType(ticketFromClientDTO.ticketType());
        existingTicket.setUser(user);
        existingTicket.setDescription(ticketFromClientDTO.description());
        existingTicket.setLocation(ticketFromClientDTO.location());
        existingTicket.setCreatedAt(OffsetDateTime.now());
        existingTicket.setImgURI(ticketFromClientDTO.imgURI());
        existingTicket.setPetName(ticketFromClientDTO.petName());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return TicketMapper.mapToTicketDTO(updatedTicket);
    }

    @Override
    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }

    private User getUserById(TicketFromClientDTO ticketFromClientDTO) {
        return userRepository
                .findById(ticketFromClientDTO.userID())
                .orElseThrow(() -> new UserNotFoundException(ticketFromClientDTO.userID()));
    }

}
