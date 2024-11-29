package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.response.ResponseFromClientDTO;
import com.example.lostpethelper.dto.response.ResponseToClientDTO;

import java.util.List;

public interface ResponseService {
    List<ResponseToClientDTO> findAllResponses();

    ResponseToClientDTO findResponseById(Integer id);

    ResponseToClientDTO createResponse(ResponseFromClientDTO responseFromClientDTO);

    ResponseToClientDTO updateResponseById(Integer id, ResponseFromClientDTO responseFromClientDTO);

    void deleteResponseById(Integer id);
}
