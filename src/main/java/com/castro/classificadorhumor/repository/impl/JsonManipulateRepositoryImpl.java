package com.castro.classificadorhumor.repository.impl;

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

    public List<Ticket> jsonRead() throws IOException {
        return objectMapper.readValue(new File("tickets.json"), new TypeReference<ArrayList<Ticket>>() {
        });
    }

    public List<Ticket> updateJson(List<Ticket> tickets) throws IOException {
        objectMapper.writeValue(new File("tickets.json"), tickets);
        return tickets;
    }
}