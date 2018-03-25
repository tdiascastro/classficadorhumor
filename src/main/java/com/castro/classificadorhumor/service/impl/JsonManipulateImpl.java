package com.castro.classificadorhumor.service.impl;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.service.JsonManipulateService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonManipulateImpl implements JsonManipulateService {

    private ObjectMapper objectMapper;

    @Autowired
    public JsonManipulateImpl(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Ticket> list() throws IOException {
        return objectMapper.readValue(new File("tickets.json"), new TypeReference<ArrayList<Ticket>>() {
        });
    }
}
