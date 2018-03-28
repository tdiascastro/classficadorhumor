package com.castro.classificadorhumor.service;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketValidationService {

    List<Ticket> priorizedTickets(LocalDate startDate, LocalDate endDate, String priority) throws IOException;
}
