package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.ResponseDTO;
import java.util.List;

public interface ResponseService {
    List<ResponseDTO> findAllResponses();

    ResponseDTO findResponseById(Integer id);

    ResponseDTO createResponse(ResponseDTO responseDTO);

    ResponseDTO updateResponseById(Integer id, ResponseDTO responseDTO);

    void deleteResponseById(Integer id);
}
