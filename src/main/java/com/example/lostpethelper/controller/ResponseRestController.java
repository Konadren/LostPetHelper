package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.response.ResponseFromClientDTO;
import com.example.lostpethelper.dto.response.ResponseToClientDTO;
import com.example.lostpethelper.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/responses")
public class ResponseRestController {

    private final ResponseService responseService;

    @Autowired
    public ResponseRestController(ResponseService responseService) {
        this.responseService = responseService;
    }
    
    // метод только для админа
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResponseToClientDTO>> getAllResponses(){
        List<ResponseToClientDTO> responses = responseService.findAllResponses();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseToClientDTO> getResponseById(@PathVariable Integer id) {
        ResponseToClientDTO responseFromClientDTO = responseService.findResponseById(id);
        return new ResponseEntity<>(responseFromClientDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseToClientDTO> createResponse(
            @Valid @RequestBody ResponseFromClientDTO responseFromClientDTO) {
        ResponseToClientDTO createdResponse = responseService.createResponse(responseFromClientDTO);
        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseToClientDTO> updateResponseById(
            @PathVariable Integer id, @Valid @RequestBody ResponseFromClientDTO responseFromClientDTO) {
        ResponseToClientDTO updatedResponseFromClientDTO = responseService.updateResponseById(id, responseFromClientDTO);
        return new ResponseEntity<>(updatedResponseFromClientDTO, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponseById(@PathVariable Integer id) {
        responseService.deleteResponseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
