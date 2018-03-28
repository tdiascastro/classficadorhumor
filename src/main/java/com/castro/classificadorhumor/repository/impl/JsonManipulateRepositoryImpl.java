package com.castro.classificadorhumor.repository.impl;

import com.castro.classificadorhumor.exception.JsonException;
import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.repository.JsonManipulateRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonManipulateRepositoryImpl implements JsonManipulateRepository {

    private ObjectMapper objectMapper;

    @Autowired
    public JsonManipulateRepositoryImpl(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Ticket> jsonRead() throws JsonException {
        try {
            return objectMapper.readValue(new File("tickets.json"), new TypeReference<ArrayList<Ticket>>() {
            });
        } catch (IOException e) {
            throw new JsonException(e.getMessage());
        }
    }

    public List<Ticket> updateJson(List<Ticket> tickets) throws JsonException {
        try {
            objectMapper.writeValue(new File("tickets.json"), tickets);
            return tickets;
        } catch (IOException e) {
            throw new JsonException(e.getMessage());
        }
    }
}
