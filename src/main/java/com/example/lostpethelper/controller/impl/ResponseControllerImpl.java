package com.example.lostpethelper.controller.impl;

import com.example.lostpethelper.controller.ResponseController;
import com.example.lostpethelper.dto.rest.response.ResponseRq;
import com.example.lostpethelper.dto.rest.response.ResponseRs;
import com.example.lostpethelper.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponseControllerImpl implements ResponseController {

    private final ResponseService responseService;

//    // метод только для админа
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<ResponseToClientDTO>> getAllResponses(){
//        List<ResponseToClientDTO> responses = responseService.findAllResponses();
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseToClientDTO> getResponseById(@PathVariable Integer id) {
//        ResponseToClientDTO responseFromClientDTO = responseService.findResponseById(id);
//        return new ResponseEntity<>(responseFromClientDTO, HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
//    @PostMapping
//    public ResponseEntity<ResponseToClientDTO> createResponse(
//            @Valid @RequestBody ResponseRq responseRq) {
//        ResponseToClientDTO createdResponse = responseService.createResponse(responseRq);
//        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseToClientDTO> updateResponseById(
//            @PathVariable Integer id, @Valid @RequestBody ResponseRq responseRq) {
//        ResponseToClientDTO updatedResponseFromClientDTO = responseService.updateResponseById(id, responseRq);
//        return new ResponseEntity<>(updatedResponseFromClientDTO, HttpStatus.NO_CONTENT);
//    }

    @PreAuthorize("hasAuthority('ROLE_USER')  or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponseById(@PathVariable Integer id) {
        responseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseRs create(ResponseRq rq) {
        return responseService.create(rq);
    }

    @Override
    public ResponseRs update(ResponseRq rq, String id) {
        return responseService.update(id, rq);
    }

    @Override
    public ResponseRs get(String id) {
        return responseService.find(id);
    }

    @Override
    public ResponseRs getAll() {
        return responseService.findAll();
    }

    @Override
    public void delete(String id) {

    }
}
