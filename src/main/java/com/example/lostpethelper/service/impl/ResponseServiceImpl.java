package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.response.ResponseFromClientDTO;
import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.exception.ResponseNotFoundException;
import com.example.lostpethelper.exception.TicketNotFoundException;
import com.example.lostpethelper.exception.UserNotFoundException;
import com.example.lostpethelper.mapper.ResponseMapper;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.ResponseRepository;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Override
    public List<ResponseToClientDTO> findAllResponses() {
        List<Response> responses = responseRepository.findAll();

        return responses.stream()
                .map(ResponseMapper::mapToResponseToClientDTO)
                .toList();
    }

    @Override
    public ResponseToClientDTO findResponseById(Integer id) {
        Response response = responseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseNotFoundException(id));

        return ResponseMapper.mapToResponseToClientDTO(response);
    }

    @Override
    @Transactional
    public ResponseToClientDTO createResponse(ResponseFromClientDTO responseFromClientDTO) {
        User user = getUserById(responseFromClientDTO);
        Ticket ticket = getTicketById(responseFromClientDTO);

        Response response = ResponseMapper.mapToResponse(responseFromClientDTO, null, user, ticket); // todo: криво-косо, почитать про MapStruct

        Response createdResponse = responseRepository.save(response);

        return ResponseMapper.mapToResponseToClientDTO(createdResponse);
    }

    @Override
    @Transactional
    public ResponseToClientDTO updateResponseById(Integer id, ResponseFromClientDTO responseFromClientDTO) {
        Response existingResponse = responseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseNotFoundException(id));
        User user = getUserById(responseFromClientDTO);
        Ticket ticket = getTicketById(responseFromClientDTO);

        existingResponse.setMessage(responseFromClientDTO.message());
        existingResponse.setTicket(ticket);
        existingResponse.setUser(user);
        existingResponse.setImgURI(responseFromClientDTO.imgURI());
        existingResponse.setLocation(responseFromClientDTO.location());
        existingResponse.setCreatedAt(OffsetDateTime.now());

        Response updatedResponse = responseRepository.save(existingResponse);

        return ResponseMapper.mapToResponseToClientDTO(updatedResponse);
    }

    private Ticket getTicketById(ResponseFromClientDTO responseFromClientDTO) {
        return ticketRepository
                .findById(responseFromClientDTO.ticketId())
                .orElseThrow(() -> new TicketNotFoundException(responseFromClientDTO.ticketId()));
    }

    private User getUserById(ResponseFromClientDTO responseFromClientDTO) {
        return userRepository
                .findById(responseFromClientDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(responseFromClientDTO.userId()));
    }

    @Override
    public void deleteResponseById(Integer id) {
        responseRepository.deleteById(id);
        System.out.printf("Response with id = %d was deleted%n", id);
    }
}
