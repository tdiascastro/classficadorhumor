package com.castro.classificadorhumor.controllers;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.repository.JsonManipulateService;
import com.castro.classificadorhumor.repository.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController implements Serializable {

    private JsonManipulateService jsonService;
    private TicketValidationService ticketService;

    @Autowired
    public TicketController(final JsonManipulateService jsonService, final TicketValidationService ticketService) {
        this.jsonService = jsonService;
        this.ticketService = ticketService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ticket>> listTickets() throws IOException {
        List<Ticket> tickets = ticketService.priorizedTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
