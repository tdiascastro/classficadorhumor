package com.castro.classificadorhumor.controllers;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.service.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController implements Serializable {

    private TicketValidationService ticketService;

    @Autowired
    public TicketController(final TicketValidationService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Ticket>> listTickets(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate endDate,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "quantity", required = false, defaultValue = "10") int quantity) throws IOException {
        List<Ticket> tickets = ticketService.priorizedTickets(startDate, endDate, priority, page, quantity);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
