package com.example.lostpethelper.service.impl;

import com.example.lostpethelper.dto.rest.response.ResponseRq;
import com.example.lostpethelper.exception.ResponseNotFoundException;
import com.example.lostpethelper.mapper.ResponseMapper1;
import com.example.lostpethelper.model.Response;
import com.example.lostpethelper.model.Ticket;
import com.example.lostpethelper.model.User;
import com.example.lostpethelper.repository.ResponseRepository;
import com.example.lostpethelper.repository.TicketRepository;
import com.example.lostpethelper.repository.UserRepository;
import com.example.lostpethelper.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Override
    public List<Response> findAll() {
        List<Response> responses = responseRepository.findAll();

        return responses.stream()
                .map(ResponseMapper1::mapToResponseToClientDTO)
                .toList();
    }

    @Override
    public Response find(Integer id) {
        Response response = responseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseNotFoundException(id));

        return ResponseMapper1.mapToResponseToClientDTO(response);
    }

    @Override
    @Transactional
    public Response create(Response rq) {
        User user = getUserById(rq);
        Ticket ticket = getTicketById(rq);

        Response response = ResponseMapper1.mapToResponse(rq, null, user, ticket); // todo: криво-косо, почитать про MapStruct

        Response createdResponse = responseRepository.save(response);

        return ResponseMapper1.mapToResponseToClientDTO(createdResponse);
    }

    @Override
    @Transactional
    public Response update(Integer id, ResponseRq responseRq) {
        Response existingResponse = responseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseNotFoundException(id));
        User user = getUserById(responseRq);
        Ticket ticket = getTicketById(responseRq);

        existingResponse.setMessage(responseRq.message());
        existingResponse.setTicket(ticket);
        existingResponse.setUser(user);
        existingResponse.setImgURI(responseRq.imgURI());
        existingResponse.setLocation(responseRq.location());
        existingResponse.setCreatedAt(OffsetDateTime.now());

        Response updatedResponse = responseRepository.save(existingResponse);

        return ResponseMapper1.mapToResponseToClientDTO(updatedResponse);
    }

    @Override
    public void delete(Integer id) {
        responseRepository.deleteById(id);
        System.out.printf("Response with id = %d was deleted%n", id);
    }
}
