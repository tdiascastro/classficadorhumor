package com.castro.classificadorhumor.controllers;

import com.castro.classificadorhumor.models.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> listTickets() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("tickets.json"), new TypeReference<ArrayList<Ticket>>(){});
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
