package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.TicketDTO;
import com.example.lostpethelper.mapper.TicketMapper;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        User user = getUserById(ticketDTO);
        Ticket ticket = TicketMapper.mapToTicket(ticketDTO, null, user);

        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketMapper.mapToTicketDTO(savedTicket);
    }

    @Override
    public List<TicketDTO> findAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(TicketMapper::mapToTicketDTO)
                .toList();
    }

    @Override
    public TicketDTO findTicketById(Integer id) {
        Ticket ticket = ticketRepository
                .findById(id)
                .orElseThrow(() ->  new NoSuchElementException("Ticket not found"));

        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public TicketDTO updateTicketById(Integer id, TicketDTO ticketDTO){
        Ticket existingTicket = ticketRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found"));

        User user = getUserById(ticketDTO);

        existingTicket.setTicketType(ticketDTO.ticketType());
        existingTicket.setUser(user);
        existingTicket.setDescription(ticketDTO.description());
        existingTicket.setLocation(ticketDTO.location());
        existingTicket.setCreatedAt(ticketDTO.createdAt());
        existingTicket.setImgURI(ticketDTO.imgURI());
        existingTicket.setPetName(ticketDTO.petName());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return TicketMapper.mapToTicketDTO(updatedTicket);
    }

    @Override
    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }

    private User getUserById(TicketDTO ticketDTO) {
        return userRepository
                .findById(ticketDTO.userID())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

}
