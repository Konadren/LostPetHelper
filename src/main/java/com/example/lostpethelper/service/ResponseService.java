package com.example.lostpethelper.service;

import com.example.lostpethelper.dto.rest.response.ResponseRq;
import com.example.lostpethelper.model.Response;

import java.util.List;

public interface ResponseService {

    List<Response> findAll();

    Response find(Integer id);

    Response create(ResponseRq responseRq);

    Response update(Integer id, ResponseRq responseRq);

    void delete(Integer id);
}
