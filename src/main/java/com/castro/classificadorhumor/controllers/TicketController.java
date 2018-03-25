package com.castro.classificadorhumor.controllers;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.service.JsonManipulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    private JsonManipulateService service;

    @Autowired
    public TicketController(final JsonManipulateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> listTickets() throws IOException {
        List<Ticket> tickets = service.list();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
