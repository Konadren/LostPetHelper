package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.ResponseDTO;
import com.example.lostpethelper.mapper.ResponseMapper;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.ResponseRepository;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<ResponseDTO> findAllResponses() {
        List<Response> responses = responseRepository.findAll();

        return responses.stream()
                .map(ResponseMapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public ResponseDTO findResponseById(Integer id) {
        Response response = responseRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Response not found"));

        return ResponseMapper.mapToResponseDTO(response);
    }

    @Override
    @Transactional
    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        User user = getUserById(responseDTO);
        Ticket ticket = getTicketById(responseDTO);

        Response response = ResponseMapper.mapToResponse(responseDTO, null, user, ticket); // todo: криво-косо, почитать про MapStruct

        Response createdResponse = responseRepository.save(response);

        return ResponseMapper.mapToResponseDTO(createdResponse);
    }

    @Override
    @Transactional
    public ResponseDTO updateResponseById(Integer id, ResponseDTO responseDTO) {
        Response existingResponse = responseRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Response not found"));
        User user = getUserById(responseDTO);
        Ticket ticket = getTicketById(responseDTO);

        existingResponse.setMessage(responseDTO.message());
        existingResponse.setTicket(ticket);
        existingResponse.setUser(user);
        existingResponse.setImgURI(responseDTO.imgURI());
        existingResponse.setLocation(responseDTO.location());
        existingResponse.setCreatedAt(responseDTO.createdAt());

        Response updatedResponse = responseRepository.save(existingResponse);

        return ResponseMapper.mapToResponseDTO(updatedResponse);
    }

    private Ticket getTicketById(ResponseDTO responseDTO) {
        return ticketRepository
                .findById(responseDTO.ticketId())
                .orElseThrow(() -> new NoSuchElementException("Ticket not found"));
    }

    private User getUserById(ResponseDTO responseDTO) {
        return userRepository
                .findById(responseDTO.userId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void deleteResponseById(Integer id) {
        responseRepository.deleteById(id);
        System.out.printf("Response with id = %d was deleted%n", id);
    }
}