package com.example.lostpethelper.controller;

import com.example.lostpethelper.dto.ResponseDTO;
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
    public ResponseEntity<List<ResponseDTO>> getAllResponses(){
        List<ResponseDTO> responses = responseService.findAllResponses();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable Integer id) {
        ResponseDTO responseDTO = responseService.findResponseById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse(@Valid @RequestBody ResponseDTO responseDTO) {
        ResponseDTO createdResponse = responseService.createResponse(responseDTO);
        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponseById(@PathVariable Integer id, @Valid @RequestBody ResponseDTO responseDTO) {
        ResponseDTO updatedResponseDTO = responseService.updateResponseById(id, responseDTO);



        return new ResponseEntity<>(updatedResponseDTO, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponseById(@PathVariable Integer id) {
        responseService.deleteResponseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
